package dev.reprator.video.platform.extra.models

const val MIME_AUDIO = "audio"
const val MIME_VIDEO = "video"

data class MpdInfo(
    val totalDuration: Float,
    val adaptationSets: List<AdaptationSet>
)

sealed interface AdaptationSet {
    val mimeType: String
    val defaultType: String
    val lang: String
    val group: Int
    val representation: List<Representation>
}

data class AdaptationSetAudio(
    override val mimeType: String,
    override val lang: String,
    override val group: Int,
    val minBandwidth: Long,
    val maxBandwidth: Long,
    val segmentAlignment: Boolean,
    override val representation: List<AudioRepresentation> = emptyList(),
    override val defaultType: String = MIME_AUDIO
): AdaptationSet

data class AdaptationSetVideo(
    override val group: Int,
    override val mimeType: String,
    override val lang: String,
    val par: String,
    val segmentAlignment: Boolean,
    val minBandwidth: Long,
    val maxBandwidth: Long,
    val minWidth: Long,
    val maxWidth: Long,
    val minHeight: Long,
    val maxHeight: Long,
    val startWithSAP: Int,
    override val representation: List<VideoRepresentation> = emptyList(),
    override val defaultType: String = MIME_VIDEO
): AdaptationSet

sealed interface Representation{
    val id: String
    val bandwidth: Long
}

data class VideoRepresentation(
    override val id: String,
    override val bandwidth: Long,
    val codecs: String,
    val frameRate: String,
    val segmentTemplate: SegmentTemplate,
    val width: Long,
    val height: Long,
) : Representation

data class AudioRepresentation(
    override val id: String,
    override val bandwidth: Long,
    val codecs: String,
    val audioSamplingRate: Long,
    val segmentTemplate: SegmentTemplate,
    val audioChannelConfiguration: AudioChannelConfiguration,
) : Representation

data class SegmentTemplate(
    val timescale: Long,
    val duration: Long,
    val media: String,
    val initialization: String,
    val startNumber: Int
)

data class AudioChannelConfiguration(
    val schemeIdUri: String,
    val value: Int
)