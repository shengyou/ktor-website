package io.kraftsman.ktor.website

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    routing {
        get("/") {
            //language=HTML
            call.respondText("<!doctype html>\n<html lang='zh-Hant'>\n<head>\n    <meta charset='UTF-8'>\n    <meta name='viewport'\n          content='width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0'>\n    <meta http-equiv='X-UA-Compatible' content='ie=edge'>\n    <title>Hello, world</title>\n</head>\n<body>\n    <h1>Hello, world</h1>\n    <p>a sample Ktor application</p>\n</body>\n</html>", ContentType.Text.Html, HttpStatusCode.OK)
        }
    }

}

