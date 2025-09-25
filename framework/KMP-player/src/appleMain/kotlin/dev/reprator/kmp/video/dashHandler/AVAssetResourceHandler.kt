package dev.reprator.kmp.video.dashHandler

import platform.AVFoundation.AVURLAsset

const val customPlaylistScheme = "cplp"
const val hlsExt = "m3u8"

interface AVAssetResourceHandler {
    fun saveOffline(asset: AVURLAsset)

    companion object {

        fun toCustomUrl(url: String): String {
            return customPlaylistScheme + url.drop(5).dropLast(3) + hlsExt
        }
    }
}