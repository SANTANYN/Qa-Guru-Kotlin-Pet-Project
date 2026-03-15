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

    private val usernameInput = `$`(byDataTestId("create-username"))
    private val emailInput = `$`(byDataTestId("create-email"))
    private val passwordInput = `$`(byDataTestId("create-password"))
    private val submitButton = `$`(byDataTestId("create-submit"))
    private val errorText = `$`(byDataTestId("create-error"))
    private val loginLink = `$`(byDataTestId("create-login"))

    @Step("Создать пользователя: username={username}, email={email}")
    fun createUser(username: String, email: String, password: String): CreateUserDialogComponent {
        usernameInput.value = username
        emailInput.value = email
        passwordInput.value = password
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
