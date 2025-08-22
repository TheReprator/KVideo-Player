package dev.reprator.video.platform.dash

import dev.reprator.video.platform.dash.models.VideoRepresentation
import java.net.HttpURLConnection
import java.net.URL

object DashSegmentLoader {

    fun downloadMpdSafe(url: String): String {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
        connection.connectTimeout = 5000
        connection.readTimeout = 5000
        connection.requestMethod = "GET"
        connection.setRequestProperty("Accept", "application/dash+xml")

        val body = connection.inputStream.bufferedReader().use { it.readText() }
        return body
    }

    fun downloadBytes(url: String): ByteArray =
        URL(url).openStream().use { it.readBytes() }

    // Replace placeholders like $RepresentationID$ or $Number$
    fun buildInitUrl(baseUrl: String, rep: VideoRepresentation): String =
        baseUrl + rep.segmentTemplate.initialization.replace("\$RepresentationID\$", rep.id)

    fun buildMediaUrl(baseUrl: String, rep: VideoRepresentation, number: Int): String =
        baseUrl + rep.segmentTemplate.media
            .replace("\$RepresentationID\$", rep.id)
            .replace("\$Number\$", number.toString())

}


object Http {
    fun getBytes(url: String): ByteArray {
        val c = URL(url).openConnection() as HttpURLConnection
        c.connectTimeout = 10_000
        c.readTimeout = 10_000
        c.instanceFollowRedirects = true
        c.setRequestProperty("User-Agent", "KVideo/1.0")
        if (c.responseCode in 200..299) {
            return c.inputStream.use { it.readBytes() }
        } else error("HTTP ${c.responseCode} for $url")
    }
}

object DashUrls {
    fun buildBaseUrl(mpdUrl: String): String = mpdUrl.substringBeforeLast("/") + "/"

    fun initUrl(base: String, mediaPath: String) = resolve(base, mediaPath)
    fun segUrl(base: String, pattern: String, number: Long) =
        resolve(base, pattern.replace("\$Number\$", number.toString()))

    private fun resolve(base: String, path: String): String =
        if (path.startsWith("http")) path else base + path
}
