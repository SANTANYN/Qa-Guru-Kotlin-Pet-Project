package frontend.components

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

/**
 * Компонент диалога создания аккаунта.
 */
class CreateUserDialogComponent {

    private val container = `$`(".dialog").shouldBe(Condition.visible)
    private val usernameInput = container.`$`(byDataTestId("create-username"))
    private val emailInput = container.`$`(byDataTestId("create-email"))
    private val passwordInput = container.`$`(byDataTestId("create-password"))
    private val submitButton = container.`$`(byDataTestId("create-submit"))
    private val errorText = container.`$`(byDataTestId("create-error"))
    private val loginLink = container.`$`(byDataTestId("create-login"))

    @Step("Создать пользователя: username={username}, email={email}")
    fun createUser(username: String, email: String, password: String): CreateUserDialogComponent {
        usernameInput.sendKeys(username)
        emailInput.sendKeys(email)
        passwordInput.sendKeys(password)
        submitButton.click()
        return this
    }

    @Step("Нажать на ссылку перехода к логину")
    fun clickLoginLink(): LoginDialogComponent {
        loginLink.click()
        return LoginDialogComponent()
    }

    @Step("Проверить наличие ошибки: {expectedError}")
    fun shouldHaveError(expectedError: String) {
        errorText.shouldHave(text(expectedError))
    }
}
