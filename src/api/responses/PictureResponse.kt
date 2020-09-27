package picfinder.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureResponse(
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "query")
    val query: String
)