@file:JsModule("video.js")
@file:JsNonModule

package dev.reprator.video.platform.external

import dev.reprator.video.modals.InternalVideoPlayer
import dev.reprator.video.modals.InternalVideoInitOptionsModal
import web.dom.Element

@JsName("default")
actual external fun videojs(
    id: Element,
    options: InternalVideoInitOptionsModal
): InternalVideoPlayer

