package picfinder

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing
import picfinder.data.storage.database.DatabaseFactory

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    DatabaseFactory.init()

    routing {
        get("/some") {
            call.respond("OK")
        }
    }
}

