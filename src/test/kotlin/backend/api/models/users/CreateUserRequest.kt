package backend.api.models.users

data class CreateUserRequest(
    val username: String?,
    val email: String?,
    val password: String?,
)
