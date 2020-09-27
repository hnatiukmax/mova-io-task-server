package picfinder.api.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import picfinder.api.PUB
import picfinder.api.SERVER_ERROR
import picfinder.api.responses.asErrorResponse
import picfinder.data.network.entity.asPictureResponse
import picfinder.data.repositories.PictureRepository

const val PICTURE_ENDPOINT = "$PUB/picture"

@KtorExperimentalLocationsAPI
@Location(PICTURE_ENDPOINT)
class Picture(val picSourceId: Int, val query: String)

@KtorExperimentalLocationsAPI
fun Route.picture(pictureRepository: PictureRepository) {

    get<Picture> {
        try {
            val pictureResponse = pictureRepository.getPictureBySourceId(it.picSourceId, it.query)
            call.respond(HttpStatusCode.InternalServerError, pictureResponse.asPictureResponse)
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, SERVER_ERROR.asErrorResponse)
        }
    }
}