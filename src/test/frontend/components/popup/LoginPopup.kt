package frontend.components.popup

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.executeJavaScript
import com.codeborne.selenide.SelenideElement
import frontend.helpers.ExplicitWaits
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step
import java.time.Duration

/**
 * Компонент диалога логина.
 *
 * Поля [md-outlined-text-field] — веб-компоненты с Shadow DOM: нативный input внутри shadow root.
 * [setValue]/присваивание `.value` на хосте даёт InvalidElementStateException, поэтому значение
 * выставляется через JS на внутренний input и диспатчится `input` для Vue v-model.
 */
class LoginPopup {

    private val emailInput = `$`(byDataTestId("login-email"))
    private val passwordInput = `$`(byDataTestId("login-password"))
    private val submitButton = `$`(byDataTestId("login-submit"))
    private val errorText = `$`(byDataTestId("login-error"))

    @Step("Заполнить форму логина: email={email}")
    fun fillLoginForm(email: String, password: String): LoginPopup {
        fillMaterialOutlinedField(emailInput, email)
        fillMaterialOutlinedField(passwordInput, password)
        return this
    }

    @Step("Отправить форму логина")
    fun submitLogin(): LoginPopup {
        submitButton.click()
        return this
    }

    /** Заполнить поля и нажать Login (шорткат). */
    @Step("Залогиниться с email={email}")
    fun login(email: String, password: String): LoginPopup {
        fillLoginForm(email, password)
        return submitLogin()
    }

    @Step("Получить текст ошибки логина")
    fun errorMessage(): String = errorText.text ?: ""

    /**
     * Текст ошибки после сабмита: явное ожидание [expectedMessage], затем чтение из DOM.
     * Без ожидания чтение сразу после сабмита с заполненными полями было бы недетерминировано (ответ API).
     */
    @Step("Получить текст ошибки (ожидание): {expectedMessage}")
    fun getErrorText(expectedMessage: String): String {
        waitForErrorMessage(expectedMessage)
        return errorMessage()
    }

    /**
     * Явное ожидание: [WebDriverWait] + [ExpectedConditions.textToBe] по локатору ошибки.
     * Нужно после сабмита с заполненными полями (ответ API асинхронный; клик в Selenium не ждёт fetch).
     */
    @Step("Дождаться текста ошибки логина: {expectedMessage}")
    fun waitForErrorMessage(
        expectedMessage: String,
        timeout: Duration = Duration.ofSeconds(15),
    ): LoginPopup {
        ExplicitWaits.waitForExactText(byDataTestId("login-error"), expectedMessage, timeout)
        return this
    }

    private fun fillMaterialOutlinedField(host: SelenideElement, value: String) {
        executeJavaScript<Unit>(
            """
            const host = arguments[0];
            const input = host.shadowRoot && host.shadowRoot.querySelector('input');
            if (!input) {
              throw new Error('md-outlined-text-field: no input in shadow root');
            }
            input.value = arguments[1];
            input.dispatchEvent(new Event('input', { bubbles: true, composed: true }));
            """.trimIndent(),
            host,
            value
        )
    }
}
