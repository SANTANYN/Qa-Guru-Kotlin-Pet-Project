package frontend.components.popup

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

/**
 * Компонент диалога создания аккаунта.
 */
class CreateUserPopup {

    private val usernameInput = `$`(byDataTestId("create-username"))
    private val emailInput = `$`(byDataTestId("create-email"))
    private val passwordInput = `$`(byDataTestId("create-password"))
    private val submitButton = `$`(byDataTestId("create-submit"))
    private val errorText = `$`(byDataTestId("create-error"))
    private val loginLink = `$`(byDataTestId("create-login"))
    private val title = `$`(byDataTestId("create-title"))

    @Step("Создать пользователя: username={username}, email={email}")
    fun createUser(username: String, email: String, password: String): CreateUserPopup {
        usernameInput.value = username
        emailInput.value = email
        passwordInput.value = password
        submitButton.click()
        return this
    }

    @Step("Нажать на ссылку перехода к логину")
    fun clickLoginLink(): LoginPopup {
        loginLink.click()
        return LoginPopup()
    }

    fun getErrorText(): String = errorText.text ?: ""

    @Step("Диалог создания аккаунта открыт (заголовок виден)")
    fun isOpen(): Boolean = title.`is`(visible)

    @Step("Текст заголовка диалога создания аккаунта")
    fun getTitleText(): String = title.text()
}
