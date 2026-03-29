package backend.api.models

data class AuthLoginRequest(
    val email: String?,
    val password: String?,
)
