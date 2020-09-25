package picfinder

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.*
import picfinder.api.routing.picSource
import picfinder.data.repositories.PicSourceRepository
import picfinder.data.storage.database.DatabaseFactory

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
@KtorExperimentalAPI
@KtorExperimentalLocationsAPI
fun Application.module(testing: Boolean = false) {

    DatabaseFactory.init()

    install(Locations)
    install(DefaultHeaders)
    install(ContentNegotiation) {
        gson()
    }

    val picSourceRepository = PicSourceRepository()

    routing {
        picSource(picSourceRepository)
        get("/some") {
            call.respond("OK")
        }
    }
}

