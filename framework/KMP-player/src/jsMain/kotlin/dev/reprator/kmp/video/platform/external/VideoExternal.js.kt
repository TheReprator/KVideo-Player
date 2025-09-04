@file:JsModule("video.js")
@file:JsNonModule

package dev.reprator.kmp.video.platform.external

import dev.reprator.kmp.video.modals.InternalVideoPlayer
import dev.reprator.kmp.video.modals.InternalVideoInitOptionsModal
import web.dom.Element

@JsName("default")
actual external fun videojs(
    id: Element,
    options: InternalVideoInitOptionsModal
): InternalVideoPlayer

