package picfinder

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.locations.*
import io.ktor.routing.routing
import io.ktor.util.*
import picfinder.api.routing.pictureSource
import picfinder.api.routing.picture
import picfinder.api.routing.refresh
import picfinder.data.network.client.RestClient
import picfinder.data.repositories.PictureSourceRepository
import picfinder.data.repositories.PictureRepository
import picfinder.data.storage.database.DatabaseFactory
import picfinder.server.IMGUR_API
import picfinder.server.UNSPLASH_API
import picfinder.server.extensions.fromEnv

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
@KtorExperimentalAPI
@KtorExperimentalLocationsAPI
fun Application.module(testing: Boolean = false) {

    val restClient = RestClient(unsplashUrl = UNSPLASH_API.fromEnv(), imgurUrl = IMGUR_API.fromEnv())
    val picSourceRepository = PictureSourceRepository()
    val pictureRepository = PictureRepository(restClient)

    DatabaseFactory.init()

    install(Locations)
    install(DefaultHeaders)
    install(ContentNegotiation) {
        gson()
    }

    routing {
        refresh()
        pictureSource(picSourceRepository)
        picture(pictureRepository)
    }
}