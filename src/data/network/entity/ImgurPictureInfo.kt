package picfinder.data.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ImgurPictureInfo(
    @Json(name = "tags")
    val tags: List<Tag>? = null,
    @Json(name = "images")
    val images: List<Image>? = null
) : PictureInfo(
    imageUrl = images?.getOrNull(0)?.imageUrl.orEmpty(),
    query = tags?.getOrNull(0)?.name.orEmpty()
) {

    @JsonClass(generateAdapter = true)
    data class Tag(
        @Json(name = "name")
        val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Image(
        @Json(name = "link")
        val imageUrl: String
    )
}