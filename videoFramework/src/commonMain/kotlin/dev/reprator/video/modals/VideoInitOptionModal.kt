package dev.reprator.video.modals

data class VideoInitOptionModal(
    val controls: Boolean = true,
    val autoplay: Boolean = false,
    val poster: String?,
    val preload: String = "auto",
    val muted: Boolean = false,
    val id: String?,
    val sources: List<VideoSource>)