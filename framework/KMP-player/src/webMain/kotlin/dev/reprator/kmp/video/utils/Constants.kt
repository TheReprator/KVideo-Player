package dev.reprator.kmp.video.utils

private const val VIDEO_JS_VERSION = "8.23.4"
const val VIDEO_JS_URL = "https://vjs.zencdn.net/$VIDEO_JS_VERSION/video.min.js"
const val VIDEO_JS_CSS_URL = "https://vjs.zencdn.net/$VIDEO_JS_VERSION/video-js.css"
const val VIDEO_JS_CSS_ID = "videoJsCssId"
const val VIDEO_JS_ID = "videoJsScriptId"

private val extensionMap = mapOf(
    "m3u8" to "application/x-mpegURL",
    "mpd" to "application/dash+xml",
    "mp4" to "video/mp4",
    "mov" to "video/mp4",
    "m4v" to "video/mp4",
    "mkv" to "video/mp4",
)

fun getMimetype(src: String): String {
    val ext = src.substringAfterLast('.', "").lowercase()
    return extensionMap[ext] ?: ""
}