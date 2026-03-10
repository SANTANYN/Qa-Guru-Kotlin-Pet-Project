package frontend

import frontend.components.CreateUserDialogComponent
import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@Epic("Frontend Tests")
@Feature("Authentication")
@Owner("mikhail")
class NegativeLoginTest : BaseUiTest() {

    private val homePage = HomeViewPage()

    @DisplayName("Negative login validation test")
    @ParameterizedTest(name = "Email: {0}, Password: {1}, Error: {2}")
    @Story("Login validation")
    @CsvSource(
        "'', '', 'Please enter email and password'",
        "'test@test.com', '', 'Please enter email and password'",
        "'', 'password', 'Please enter email and password'",
        "'wrong@email.com', 'wrongpass', 'Invalid email or password'"
    )
    fun `should show validation error on login`(email: String?, password: String?, expectedError: String) {
        homePage
            .openPage()
            .header().clickJoin()
        
        CreateUserDialogComponent()
            .clickLoginLink()
            .login(email ?: "", password ?: "")
            .shouldHaveError(expectedError)
    }
}
