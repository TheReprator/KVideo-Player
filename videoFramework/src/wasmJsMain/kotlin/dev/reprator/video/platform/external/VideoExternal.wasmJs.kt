package dev.reprator.video.platform.external

import dev.reprator.video.modals.InternalVideoPlayer
import dev.reprator.video.modals.InternalVideoInitOptionsModal
import web.dom.Element

@OptIn(ExperimentalWasmJsInterop::class)
@JsModule("video.js")
@JsName("default")
actual external fun videojs(
    id: Element,
    options: InternalVideoInitOptionsModal
): InternalVideoPlayer