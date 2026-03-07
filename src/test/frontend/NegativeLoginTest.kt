package frontend

import frontend.components.CreateUserDialogComponent
import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NegativeLoginTest : BaseUiTest() {

    private val homePage = HomeViewPage()

    @DisplayName("Negative login validation test")
    @ParameterizedTest(name = "Email: {0}, Password: {1}, Error: {2}")
    @CsvSource(
        "'', '', 'Please enter email and password'",
        "'test@test.com', '', 'Please enter email and password'",
        "'', 'password', 'Please enter email and password'",
        "'wrong@email.com', 'wrongpass', 'Invalid email or password'"
    )
    fun `should show validation error on login`(email: String?, password: String?, expectedError: String) {
        homePage.header().clickJoin()
        
        CreateUserDialogComponent()
            .clickLoginLink()
            .login(email ?: "", password ?: "")
            .shouldHaveError(expectedError)
    }
}
