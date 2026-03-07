package frontend.components

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import frontend.helpers.Wrappers.Companion.byDataTestId

/**
 * Компонент диалога логина.
 */
class LoginDialogComponent {

    private val container = `$`(".dialog").shouldBe(Condition.visible)
    private val emailInput = container.`$`(byDataTestId("login-email"))
    private val passwordInput = container.`$`(byDataTestId("login-password"))
    private val submitButton = container.`$`(byDataTestId("login-submit"))
    private val errorText = container.`$`(byDataTestId("login-error"))

    fun login(email: String, password: String): LoginDialogComponent {
        if (email.isNotEmpty()) emailInput.sendKeys(email)
        if (password.isNotEmpty()) passwordInput.sendKeys(password)
        submitButton.click()
        return this
    }

    fun shouldHaveError(expectedError: String) {
        errorText.shouldHave(text(expectedError))
    }
}
