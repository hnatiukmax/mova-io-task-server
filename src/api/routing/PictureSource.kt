package picfinder.api.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import picfinder.api.PUB
import picfinder.api.SERVER_ERROR
import picfinder.api.responses.asErrorResponse
import picfinder.data.repositories.PictureSourceRepository

const val PIC_SOURCE_ENDPOINT = "$PUB/picture-source"

@KtorExperimentalLocationsAPI
@Location(PIC_SOURCE_ENDPOINT)
class PictureSource

@KtorExperimentalLocationsAPI
fun Route.pictureSource(pictureSourceRepository: PictureSourceRepository) {

    get<PictureSource> {
        try {
            val picSources = pictureSourceRepository.getPictureSources()
            call.respond(HttpStatusCode.OK, picSources)
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, SERVER_ERROR.asErrorResponse)
        }
    }
}