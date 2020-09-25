package picfinder.api.extensions

import picfinder.api.responses.ErrorResponse

val String.asErrorResponse get() = ErrorResponse(this)