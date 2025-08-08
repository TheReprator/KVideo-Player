package dev.reprator.video.platform.external

import dev.reprator.video.modals.InternalVideoInitOptionsModal
import dev.reprator.video.modals.InternalVideoPlayer
import web.dom.Element

expect fun videojs(id: Element, options: InternalVideoInitOptionsModal): InternalVideoPlayer