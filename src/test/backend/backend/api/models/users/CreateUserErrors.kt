package backend.api.models.users

import backend.api.models.ErrorResponse

object CreateUserErrors {
    val emptyCredentials = ErrorResponse(400, "User details cannot be null or blank")
    val requestFailed = ErrorResponse(400, "Something went wrong. Please verify request.")
}
