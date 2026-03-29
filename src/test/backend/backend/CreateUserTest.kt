package backend

import backend.api.extension.getAsObject
import backend.api.extension.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.users.CreateUserErrors
import backend.api.models.users.CreateUserRequest
import backend.controllers.Controllers
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CreateUserTest : Controllers() {

    @Test
    @DisplayName("Positive check: create user with valid credentials")
    fun testUsersCreate() {
        val suffix = System.currentTimeMillis()
        val username = "api_user_$suffix"
        val email = "api_user_$suffix@test.com"
        val password = "Secret_pass_1"

        // POST /users/create без JWT — не зависим от пресета БД (user@autotest.com).
        val created = users.createUser(
            CreateUserRequest(username = username, email = email, password = password)
        ).getAsObject()

        val token = auth.login(email, password).getAsObject().accessToken
        try {
            val fetched = users.getUserById(token, created.id).getAsObject()
            fetched shouldBeEqualToComparingFields created
        } finally {
            users.deleteUser(token, created.id).getAsObject()
        }
    }

    @Test
    @DisplayName("Negative check: creating user with existing email should return error")
    fun testUsersCreateAlreadyExistingEmail() {
        val suffix = System.currentTimeMillis()
        val email = "dup_email_$suffix@test.com"
        val password = "dup_pass_1"

        val first = users.createUser(
            CreateUserRequest(username = "dup_a_$suffix", email = email, password = password)
        ).getAsObject()

        try {
            val response = users.createUser(
                CreateUserRequest(username = "dup_b_$suffix", email = email, password = "other")
            )
            val error = response.getErrorAsObject<ErrorResponse>()
            error shouldBe CreateUserErrors.requestFailed
        } finally {
            val token = auth.login(email, password).getAsObject().accessToken
            users.deleteUser(token, first.id).getAsObject()
        }
    }

    @ParameterizedTest(name = "Username: {0}, Email: {1}, Password: {2}")
    @DisplayName("Negative check: creating user with empty credentials should return error")
    @CsvSource(
        "'', '', ''",
        "'user', '', ''",
        "'user', '1@1.com', ''",
    )
    fun testUsersCreateEmptyCredentials(username: String, email: String, password: String) {
        val response = users.createUser(
            CreateUserRequest(username = username, email = email, password = password)
        )

        val error = response.getErrorAsObject<ErrorResponse>()
        error shouldBe CreateUserErrors.emptyCredentials
    }
}
