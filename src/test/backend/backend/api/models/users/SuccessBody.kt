package backend.api.models.users

/** Ответ DELETE /users/{id} и аналогичных success-тел. */
data class SuccessBody(
    val code: Int,
    val message: String,
)
