package backend.api.models

data class AuthResponse(
    val id: Int,
    val accessToken: String,
    val refreshToken: String,
    val createdAt: Long,
    val expireInMs: Long,
)
