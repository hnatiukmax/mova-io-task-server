package picfinder.api.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.async
import picfinder.api.PICTURE_NOT_FOUND_ERROR
import picfinder.api.PUB
import picfinder.api.SERVER_ERROR
import picfinder.api.responses.asErrorResponse
import picfinder.data.repositories.PictureRepository

const val PICTURE_ENDPOINT = "$PUB/picture"

@KtorExperimentalLocationsAPI
@Location(PICTURE_ENDPOINT)
class Picture(val picSourceId: Int, val query: String)

@KtorExperimentalLocationsAPI
fun Route.picture(pictureRepository: PictureRepository) {

    get<Picture> {
        try {
            val result = pictureRepository.getPictureBySourceId(it.picSourceId, it.query)

            if (result != null) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.NotFound,  PICTURE_NOT_FOUND_ERROR.asErrorResponse)
            }
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.InternalServerError, SERVER_ERROR.asErrorResponse)
        }
    }
}