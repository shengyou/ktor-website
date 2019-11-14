package io.kraftsman.ktor.website

import freemarker.cache.ClassTemplateLoader
import io.kraftsman.ktor.website.entities.Task
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    Database.connect(
        url = "jdbc:mysql://127.0.0.1:8889/ktor_website?useUnicode=true&characterEncoding=utf-8&useSSL=false",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "root"
    )

    routing {

        static("static") {
            resources("static")
        }

        get("/") {
            val tasks = transaction {
                Task.all().sortedByDescending { it.id }
            }

            call.respond(
                FreeMarkerContent(
                    "index.ftl",
                    mapOf("tasks" to tasks)
                )
            )
        }
    }

}

