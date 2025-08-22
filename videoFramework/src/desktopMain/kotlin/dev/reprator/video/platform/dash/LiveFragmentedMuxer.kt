package dev.reprator.video.platform.dash

import kotlinx.coroutines.*
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import org.mp4parser.BasicContainer
import org.mp4parser.IsoFile
import org.mp4parser.boxes.iso14496.part12.MovieBox
import org.mp4parser.boxes.iso14496.part12.TrackBox
import org.mp4parser.tools.ByteBufferByteChannel
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean

/**
 * LiveFragmentedMuxer:
 * - combine init.mp4(video) + init.m4a(audio) => a single init with 2 tracks
 * - append .m4s fragments from both reps as they arrive
 * - serve the growing file via simple HTTP (range support enough for MediaPlayer)
 */
class LiveFragmentedMuxer(
    private val outputFile: File
) {
    private val started = AtomicBoolean(false)
    private lateinit var raf: RandomAccessFile
    private lateinit var ch: FileChannel

    fun start() {
        check(started.compareAndSet(false, true))
        if (outputFile.exists()) outputFile.delete()
        raf = RandomAccessFile(outputFile, "rw")
        ch = raf.channel
    }

    fun stop() {
        try { ch.close() } catch (_: Throwable) {}
        try { raf.close() } catch (_: Throwable) {}
    }

    /**
     * Merge init (ftyp+moov) of video and audio.
     * Strategy:
     *  - Parse both inits as IsoFile
     *  - Take 'ftyp' from one (same brand is fine)
     *  - Merge 'moov' tracks: keep mvhd from first, append the other track’s trak box
     *  - Rewrite trackIDs to be unique (e.g., video=1, audio=2)
     *  - Write merged init to file channel
     */
    fun writeMergedInit(initVideo: ByteArray, initAudio: ByteArray) {
        val isoV = IsoFile(ByteBufferByteChannel(initVideo))
        val isoA = IsoFile(ByteBufferByteChannel(initAudio))

        val ftyp = isoV.boxes.first { it.type == "ftyp" }
        val moovV = isoV.boxes.first { it.type == "moov" } as MovieBox
        val moovA = isoA.boxes.first { it.type == "moov" } as MovieBox

        // Get trak boxes
        val trakV = moovV.boxes.filterIsInstance<TrackBox>().first()
        val trakA = moovA.boxes.filterIsInstance<TrackBox>().first()

        // Fix track-IDs to be distinct & simple (1 video, 2 audio)
        trakV.trackHeaderBox.trackId = 1
        trakA.trackHeaderBox.trackId = 2

        // Build merged moov (mvex may exist if fragmented init; keep from video and add audio’s trex into it)
        val merged = BasicContainer()
        merged.addBox(ftyp)
        val moovMerged = moovV
        // Remove any existing audio trak in moovV copy just in case and add trakA
        moovMerged.boxes.removeAll { it is TrackBox && it.trackHeaderBox.trackId != 1L }
        moovMerged.addBox(trakA)

        // Write out ftyp + moov
        merged.addBox(moovMerged)
        merged.writeContainer(ch)
    }

    /**
     * Append a raw .m4s fragment as-is.
     * Assumptions:
     *  - Each .m4s already contains [moof][mdat] for exactly one track.
     *  - The init we wrote set track IDs to match fragment tfhd/tfdt.
     *
     * If incoming tfhd.trackId doesn’t match our assigned IDs, you must rewrite IDs.
     * For the Bitmovin demo, usually the IDs match inside each init/segment pair.
     */
    fun appendFragment(fragmentBytes: ByteArray) {
        ch.write(java.nio.ByteBuffer.wrap(fragmentBytes))
        ch.force(false)
    }
}

