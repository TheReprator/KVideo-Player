package dev.reprator.video.platform.dash

import com.sun.net.httpserver.HttpServer
import java.io.File
import java.io.RandomAccessFile
import java.net.InetSocketAddress
import java.nio.channels.Channels

class GrowingFileHttpServer(private val file: File, private val port: Int = 8787) {
    private val server: HttpServer = HttpServer.create(InetSocketAddress("127.0.0.1", port), 0)

    init {
        // Very basic: supports plain GET and Range (enough for JavaFX)
        server.createContext("/live.mp4") { ex ->
            try {
                val raf = RandomAccessFile(file, "r")
                val len = raf.length()

                val range = ex.requestHeaders.getFirst("Range")
                if (range != null && range.startsWith("bytes=")) {
                    val parts = range.removePrefix("bytes=").split("-")
                    val start = parts[0].toLong()
                    val end = parts.getOrNull(1)?.takeIf { it.isNotBlank() }?.toLong() ?: (len - 1)
                    val toSend = (end - start + 1).coerceAtLeast(0)

                    ex.responseHeaders.add("Content-Type", "video/mp4")
                    ex.responseHeaders.add("Accept-Ranges", "bytes")
                    ex.responseHeaders.add("Content-Range", "bytes $start-$end/$len")
                    ex.sendResponseHeaders(206, toSend)

                    raf.channel.position(start)
                    Channels.newChannel(ex.responseBody).use { out ->
                        val buf = java.nio.ByteBuffer.allocate(256 * 1024)
                        var remaining = toSend
                        while (remaining > 0) {
                            buf.clear()
                            val read = raf.channel.read(buf)
                            if (read <= 0) break
                            buf.flip()
                            val chunk = minOf(read.toLong(), remaining).toInt()
                            buf.limit(chunk)
                            out.write(buf)
                            remaining -= chunk
                        }
                    }
                    raf.close()
                } else {
                    ex.responseHeaders.add("Content-Type", "video/mp4")
                    ex.responseHeaders.add("Accept-Ranges", "bytes")
                    ex.sendResponseHeaders(200, len)
                    raf.channel.transferTo(0, len, Channels.newChannel(ex.responseBody))
                    raf.close()
                }
            } catch (e: Exception) {
                ex.sendResponseHeaders(500, -1)
            } finally {
                ex.close()
            }
        }
    }

    fun start() = server.start()
    fun stop() = server.stop(0)
}
