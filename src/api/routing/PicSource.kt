package picfinder.api.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import picfinder.api.PUB
import picfinder.api.SERVER_ERROR
import picfinder.api.extensions.asErrorResponse
import picfinder.data.repositories.PicSourceRepository

const val PIC_SOURCE_ENDPOINT = "$PUB/pic-source"

@KtorExperimentalLocationsAPI
@Location(PIC_SOURCE_ENDPOINT)
class PicSource

@KtorExperimentalLocationsAPI
fun Route.picSource(picSourceRepository: PicSourceRepository) {

    get<PicSource> {
        try {
            val picSources = picSourceRepository.getPicSources()
            call.respond(HttpStatusCode.OK, picSources)
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, SERVER_ERROR.asErrorResponse)
        }
    }
}