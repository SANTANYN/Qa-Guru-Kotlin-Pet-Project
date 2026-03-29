package backend.api.models.auth

import backend.api.models.ErrorResponse

object AuthErrors {
    val invalidCredentials = ErrorResponse(400, "Invalid email or password")
}
