package util

import kotlinx.browser.document
import org.w3c.dom.HTMLLinkElement
import org.w3c.dom.HTMLScriptElement
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


suspend fun loadJs(url: String) {
    suspendCoroutine<Unit> { continuation ->
        val script = document.createElement("script") as HTMLScriptElement
        script.src = url
        script.type = "application/javascript"
        script.onload = {
            println("Loaded script: $url")
            continuation.resume(Unit)
        }
        script.onerror = { event, source, lineno, colno, error ->
            val errorMessage =
                "Error loading script: $url. Source: $source, Line: $lineno, Col: $colno, Error: ${error?.toString()}"
            println(errorMessage)
            continuation.resumeWithException(Exception(errorMessage))
            null
        }
        document.head?.appendChild(script)
    }
}

suspend fun loadCss(url: String) {
    suspendCoroutine<Unit> { continuation ->
        val link = document.createElement("link") as HTMLLinkElement
        link.rel = "stylesheet"
        link.href = url
        link.type = "text/css"
        link.onload = {
            println("Loaded CSS: $url")
            continuation.resume(Unit)
        }
        link.onerror = { event, source, lineno, colno, error ->
            val errorMessage =
                "Error loading CSS: $url. Source: $source, Line: $lineno, Col: $colno, Error: ${error?.toString()}"
            println(errorMessage)
            continuation.resumeWithException(Exception(errorMessage))
            null
        }
        document.head?.appendChild(link)
    }
}


fun isVideoJsFuncAvailable(): Boolean = js("typeof videojs === 'function'")

fun <T : Any> newJsObject(): T = js("({})")

fun jsTypeOf(o: Any): String {
    return js("typeof o")
}