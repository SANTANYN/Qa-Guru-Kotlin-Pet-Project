package frontend.components.popup

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

/**
 * Компонент диалога логина.
 */
class LoginPopup {

    private val emailInput = `$`(byDataTestId("login-email"))
    private val passwordInput = `$`(byDataTestId("login-password"))
    private val submitButton = `$`(byDataTestId("login-submit"))
    private val errorText = `$`(byDataTestId("login-error"))

    @Step("Залогиниться с email={email}")
    fun login(email: String, password: String): LoginPopup {
        if (email.isNotEmpty()) emailInput.value = email
        if (password.isNotEmpty()) passwordInput.value = password
        submitButton.click()
        return this
    }

    @Step("Проверить наличие ошибки: {expectedError}")
    fun shouldHaveError(expectedError: String) {
        errorText.shouldHave(text(expectedError))
    }
}
