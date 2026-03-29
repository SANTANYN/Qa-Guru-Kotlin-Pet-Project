package backend

import backend.api.extension.getAsObject
import backend.api.extension.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.auth.AuthErrors
import backend.controllers.Controllers
import backend.helpers.PresetUsers
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LoginTest : Controllers() {

    @Test
    @DisplayName("Positive check: Login with email and password")
    fun testLoginWithValidCredentials() {
        val response = auth.login(PresetUsers.EMAIL, PresetUsers.PASSWORD).getAsObject()

        response.refreshToken.length shouldBeGreaterThan 10
        response.accessToken.length shouldBeGreaterThan 10
    }

    @Test
    @DisplayName("Negative check: Login with invalid credentials should return error")
    fun testLoginWithInvalidCredentials() {
        val response = auth.login("random", "credentials")

        val error = response.getErrorAsObject<ErrorResponse>()
        error shouldBe AuthErrors.invalidCredentials
    }

    @ParameterizedTest(name = "Email: {0}, Password: {1}")
    @DisplayName("Negative check: Log in with empty credentials should return error")
    @CsvSource(
        "'', ''",
        "'user', ''",
        "'', '1@1.com'",
    )
    fun testLoginWithEmptyCredentials(email: String, password: String) {
        val response = auth.login(email, password)

        val error = response.getErrorAsObject<ErrorResponse>()
        error shouldBe AuthErrors.invalidCredentials
    }
}
