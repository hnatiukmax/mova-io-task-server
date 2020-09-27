package picfinder.data.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonDataWrapper<T>(
    @Json(name = "data")
    val data: T
)

val String.asDataResponse get() = JsonDataWrapper(this)