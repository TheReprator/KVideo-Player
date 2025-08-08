@file:OptIn(ExperimentalWasmJsInterop::class)
package dev.reprator.video.utils

import web.dom.ElementId
import web.dom.document
import web.dom.errorEvent
import web.dom.loadEvent
import web.events.Event
import web.events.EventHandler
import web.events.addHandler
import web.html.HTMLElement
import web.html.HTMLLinkElement
import web.html.HTMLScriptElement
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny
import kotlin.js.js

suspend fun loadJsScript(url: String, id: String) {
    suspendCoroutine<Unit> { continuation ->

        val script = document.createElement("script") as HTMLScriptElement
        script.src = url
        script.id = appCreateElement(id)
        script.type = "application/javascript"

        script.onload = EventHandler { _: Event ->
            println("Script loaded: $url")
            continuation.resume(Unit)
        }

     /*   script.errorEvent.addHandler {
            continuation.resumeWithException(RuntimeException("Failed to load JS script: $url"))
        }*/

        document.head.appendChild(script)
    }
}

suspend fun loadCss(url: String, id: String) {
    suspendCoroutine<Unit> { continuation ->
        val link = document.createElement("link") as HTMLLinkElement
        link.rel = "stylesheet"
        link.href = url
        link.id = appCreateElement(id)
        link.type = "text/css"

        /*link.errorEvent.addHandler {
            continuation.resumeWithException(RuntimeException("Failed to load script: $url"))
        }*/

        link.loadEvent.addHandler {
            println("CSS loaded: $url")
            continuation.resume(Unit)
        }
        document.head.appendChild(link)
    }
}


fun appGetElementById(elementId: String): HTMLElement? {
    return document.getElementById(ElementId(elementId))
}

fun appCreateElement(elementId: String): ElementId {
    return ElementId(elementId)
}

fun isVideoJsFuncAvailable(): Boolean = js("typeof videojs === 'function'")

fun <T : JsAny> newJsObject(): T = js("({})")