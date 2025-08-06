@file:JsModule("video.js")
@file:JsNonModule

package external

import kotlin.js.*
import org.w3c.dom.*
import org.w3c.dom.events.*

@JsName("default")
open external class Player(tag: Element, options: Any = definedExternally, ready: Any = definedExternally) {
    open var boundDocumentFullscreenChange_: (e: Any) -> Unit
    open var boundFullWindowOnEscKey_: (e: Any) -> Unit
    open var boundUpdateStyleEl_: (e: Any) -> Unit
    open var boundApplyInitTime_: (e: Any) -> Unit
    open var boundUpdateCurrentBreakpoint_: (e: Any) -> Unit
    open var boundHandleTechClick_: (e: Any) -> Unit
    open var boundHandleTechDoubleClick_: (e: Any) -> Unit
    open var boundHandleTechTouchStart_: (e: Any) -> Unit
    open var boundHandleTechTouchMove_: (e: Any) -> Unit
    open var boundHandleTechTouchEnd_: (e: Any) -> Unit
    open var boundHandleTechTap_: (e: Any) -> Unit
    open var boundUpdatePlayerHeightOnAudioOnlyMode_: (e: Any) -> Unit
    open var isFullscreen_: Boolean
    open var log: Any
    open var fsApi_: Any
    open var isPosterFromTech_: Boolean
    open var queuedCallbacks_: Array<Any>
    open var hasStarted_: Boolean
    open var userActive_: Boolean
    open var debugEnabled_: Boolean
    open var audioOnlyMode_: Boolean
    open var audioPosterMode_: Boolean
    open var tag: Element
    open var tagAttributes: Any
    open var languages_: Any
    open var poster_: String
    open var controls_: Boolean
    open var changingSrc_: Boolean
    open var playCallbacks_: Array<Any>
    open var playTerminatedQueue_: Array<Any>
    open var scrubbing_: Boolean
    open var el_: Element
    open var middleware_: Array<Any>
    open var spatialNavigation: Any
    open fun dispose()
    open var styleEl_: Element
    open var playerElIngest_: Any
    open fun createEl(): Element
    open var fill_: Boolean
    open var fluid_: Boolean
    open fun crossOrigin(value: String? = definedExternally): String?
    open fun width(value: Number = definedExternally): Number?
    open fun width(): Number?
    open fun width(value: String = definedExternally): Number?
    open fun height(value: Number = definedExternally): Number?
    open fun height(): Number?
    open fun height(value: String = definedExternally): Number?
    open fun dimension(dimension: String, value: Number = definedExternally): Number
    open fun dimension(dimension: String): Number
    open fun dimension(dimension: String, value: String = definedExternally): Number
    open fun fluid(bool: Boolean = definedExternally): Boolean?
    open fun fill(bool: Boolean = definedExternally): Boolean?
    open fun aspectRatio(ratio: String = definedExternally): String?
    open var aspectRatio_: String
    open var updateStyleEl_: Any
    open var loadTech_: Any
    open var techName_: String
    open var tech_: Any
    open var unloadTech_: Any
    open var textTracksJson_: Array<Any>
    open var addTechControlsListeners_: Any
    open var removeTechControlsListeners_: Any
    open var handleTechReady_: Any
    open var handleTechLoadStart_: Any
    open fun manualAutoplay_(type: Any): Promise<Unit>
    open fun updateSourceCaches_(srcObj: String = definedExternally)
    open var handleTechSourceset_: Any
    open fun hasStarted(request: Boolean): Boolean
    open var handleTechPlay_: Any
    open var handleTechRateChange_: Any
    open var handleTechWaiting_: Any
    open var handleTechCanPlay_: Any
    open var handleTechCanPlayThrough_: Any
    open var handleTechPlaying_: Any
    open var handleTechSeeking_: Any
    open var handleTechSeeked_: Any
    open var handleTechPause_: Any
    open var handleTechEnded_: Any
    open var handleTechDurationChange_: Any
    open var handleTechClick_: Any
    open var handleTechDoubleClick_: Any
    open var handleTechTap_: Any
    open var handleTechTouchStart_: Any
    open var userWasActive: Boolean
    open var handleTechTouchMove_: Any
    open var handleTechTouchEnd_: Any
    open var toggleFullscreenClass_: Any
    open fun documentFullscreenChange_(e: Any)
    open var handleTechFullscreenChange_: Any
    open fun handleTechFullscreenError_(event: Any, err: Any)
    open var togglePictureInPictureClass_: Any
    open var handleTechEnterPictureInPicture_: Any
    open var handleTechLeavePictureInPicture_: Any
    open var handleTechError_: Any
    open var handleTechTextData_: Any
    open fun getCache(): Any
    open var resetCache_: Any
    open var techCall_: Any
    open var techGet_: Any
    open fun play(): Promise<Any>?
    open var play_: Any
    open var waitToPlay_: (e: Any) -> Unit
    open fun runPlayTerminatedQueue_()
    open fun runPlayCallbacks_(param_val: Promise<Any>?)
    open fun pause()
    open fun paused(): Boolean
    open fun scrubbing(isScrubbing: Boolean = definedExternally): Boolean?
    open fun currentTime(seconds: Number = definedExternally): Number?
    open fun currentTime(): Number?
    open fun currentTime(seconds: String = definedExternally): Number?
    open var applyInitTime_: Any
    open fun duration(seconds: Number = definedExternally): Number?
    open fun remainingTime(): Number
    open fun remainingTimeDisplay(): Number
    open fun seeking(): Boolean
    open fun ended(): Boolean
    open fun networkState(): Number
    open fun readyState(): Number
    open fun bufferedPercent(): Number
    open fun bufferedEnd(): Number
    open fun volume(percentAsDecimal: Number = definedExternally): Number?
    open fun muted(muted: Boolean = definedExternally): Boolean?
    open fun defaultMuted(defaultMuted: Boolean = definedExternally): Boolean?
    open var lastVolume_: Any
    open fun supportsFullScreen(): Boolean
    open fun isFullscreen(isFS: Boolean = definedExternally): Boolean?
    open fun requestFullscreen(fullscreenOptions: Any = definedExternally): Promise<Any>
    open fun requestFullscreenHelper_(fullscreenOptions: Any): Any
    open fun exitFullscreen(): Promise<Any>
    open fun exitFullscreenHelper_(): Any
    open fun enterFullWindow()
    open var isFullWindow: Boolean
    open var docOrigOverflow: Any
    open fun fullWindowOnEscKey(event: String)
    open fun exitFullWindow()
    open fun disablePictureInPicture(value: Boolean = definedExternally): Any
    open fun isInPictureInPicture(isPiP: Boolean = definedExternally): Boolean?
    open var isInPictureInPicture_: Boolean
    open fun requestPictureInPicture(): Promise<Any>
    open fun exitPictureInPicture(): Promise<Any>
    open fun handleHotkeys(event: Event)
    open fun canPlayType(type: String): String
    open fun selectSource(sources: Array<Any>): dynamic /* Any | Boolean */
    open fun handleSrc_(source: Any, isRetry: Boolean = definedExternally): String?
    open var resetRetryOnError_: () -> Unit
    open fun src(source: Any): String?
    open var src_: Any
    open fun addSourceElement(srcUrl: String, mimeType: String = definedExternally): Boolean
    open fun removeSourceElement(srcUrl: String): Boolean
    open fun load()
    open fun reset()
    open fun doReset_()
    open fun resetControlBarUI_()
    open fun resetProgressBar_()
    open fun resetPlaybackRate_()
    open fun resetVolumeBar_()
    open fun currentSrc(): String
    open fun currentType(): String
    open fun preload(value: String /* "none" | "auto" | "metadata" */ = definedExternally): String?
    open fun autoplay(value: Boolean = definedExternally): dynamic /* Boolean? | String? */
    open fun autoplay(): dynamic /* Boolean? | String? */
    open fun autoplay(value: String /* "play" | "muted" | "any" */ = definedExternally): dynamic /* Boolean? | String? */
    open fun playsinline(value: Boolean = definedExternally): String?
    open fun loop(value: Boolean = definedExternally): Boolean?
    open fun poster(src: String = definedExternally): String?
    open var handleTechPosterChange_: Any
    open fun controls(bool: Boolean = definedExternally): Boolean?
    open fun usingNativeControls(bool: Boolean = definedExternally): Boolean?
    open var usingNativeControls_: Any
    open fun error(err: MediaError = definedExternally): MediaError?
    open fun error(): MediaError?
    open fun error(err: String = definedExternally): MediaError?
    open fun error(err: Number = definedExternally): MediaError?
    open var error_: MediaError
    open fun reportUserActivity(event: Any)
    open var userActivity_: Boolean
    open fun userActive(bool: Boolean = definedExternally): Boolean?
    open var listenForUserActivity_: Any
    open fun playbackRate(rate: Number = definedExternally): Number?
    open fun defaultPlaybackRate(rate: Number = definedExternally): Number?
    open fun isAudio(bool: Boolean = definedExternally): Boolean?
    open var isAudio_: Boolean
    open fun updatePlayerHeightOnAudioOnlyMode_()
    open fun enableAudioOnlyUI_()
    open fun disableAudioOnlyUI_()
    open fun audioOnlyMode(value: Boolean = definedExternally): dynamic /* Promise<Any> | Boolean */
    open fun enablePosterModeUI_()
    open fun disablePosterModeUI_()
    open fun audioPosterMode(value: Boolean = definedExternally): dynamic /* Promise<Any> | Boolean */
    open fun addTextTrack(kind: String = definedExternally, label: String = definedExternally, language: String = definedExternally): TextTrack?
    open fun removeRemoteTextTrack(obj: Any = definedExternally): Nothing?
    open fun getVideoPlaybackQuality(): Any?
    open fun videoWidth(): Number
    open fun videoHeight(): Number
    open fun language(code: String = definedExternally): String?
    open var language_: String
    open fun languages(): Array<Any>
    open fun toJSON(): Any
    open var updateCurrentBreakpoint_: Any
    open var breakpoint_: String
    open var removeCurrentBreakpoint_: Any
    open fun breakpoints(breakpoints: Any = definedExternally): Any
    open fun breakpoints(): Any
    open fun breakpoints(breakpoints: Boolean = definedExternally): Any
    open var breakpoints_: Any
    open fun responsive(value: Boolean = definedExternally): Boolean?
    open var responsive_: Any
    open fun currentBreakpoint(): String
    open fun currentBreakpointClass(): String
    open fun loadMedia(media: Any, ready: Function<*>)
    open fun getMedia(): Player
    open fun debug(enabled: Boolean): Boolean?
    open var previousLogLevel_: Any
    open fun playbackRates(newRates: Array<Number> = definedExternally): Array<Number>
    open fun hasPlugin(name: String): Boolean
    open fun usingPlugin(name: String): Boolean
    open fun videoTracks(): VideoTrackList
    open fun audioTracks(): AudioTrackList
    open fun textTracks(): TextTrackList
    open fun remoteTextTracks(): TextTrackList
    open fun on(eventName: String, callback: Any = definedExternally)
    open fun off()
    open fun isDisposed(): Boolean?

    companion object {
        fun getTagSettings(tag: Element): Any
        var players: Any
    }
}