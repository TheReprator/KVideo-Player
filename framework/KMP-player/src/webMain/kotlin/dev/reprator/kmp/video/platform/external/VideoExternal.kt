package dev.reprator.kmp.video.platform.external

import dev.reprator.kmp.video.modals.InternalVideoInitOptionsModal
import dev.reprator.kmp.video.modals.InternalVideoPlayer
import web.dom.Element

expect fun videojs(id: Element, options: InternalVideoInitOptionsModal): InternalVideoPlayer