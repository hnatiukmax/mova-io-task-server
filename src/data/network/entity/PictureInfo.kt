package picfinder.data.network.entity

import picfinder.api.responses.PictureResponse

open class PictureInfo(
    val imageUrl: String,
    val query: String
)

val PictureInfo.asPictureResponse get() = PictureResponse(imageUrl, query)