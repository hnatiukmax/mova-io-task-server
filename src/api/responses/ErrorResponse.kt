package picfinder.api.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val message: String
)

val String.asErrorResponse get() = ErrorResponse(this)