package picfinder.data.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UnsplashPictureInfo(
    @Json(name = "alt_description")
    val description: String,
    @Json(name = "urls")
    val urls: Urls
) : PictureInfo(imageUrl = urls.full, query = description) {

    @JsonClass(generateAdapter = true)
    class Urls(
        @Json(name = "raw")
        val raw: String,
        @Json(name = "full")
        val full: String,
        @Json(name = "regular")
        val regular: String,
        @Json(name = "small")
        val small: String
    )
}