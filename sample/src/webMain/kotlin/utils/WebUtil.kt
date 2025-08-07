package utils

import web.dom.ElementId
import web.dom.document
import web.events.ERROR
import web.events.Event
import web.events.EventHandler
import web.events.removeEventListener
import web.html.HTMLElement
import web.html.HTMLLinkElement
import web.html.HTMLScriptElement
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
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
            script.removeEventListener(Event.ERROR, {
                print("remove error event from script loader")
            })
            continuation.resume(Unit)
        }

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
        link.onload = EventHandler { _: Event ->
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