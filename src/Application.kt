package io.kraftsman.ktor.website

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing {
        get("/") {
            call.respondHtml {
                head {
                    title { +"Hello, HTML DSL" }
                }
                body {
                    h1 { +"Hello, HTML DSL" }
                    p { +"a sample Ktor application" }
                }
            }
        }
    }

}

