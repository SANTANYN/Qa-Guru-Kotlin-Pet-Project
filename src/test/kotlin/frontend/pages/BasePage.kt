package frontend.pages

import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Step

/**
 * Базовый page object: доступ к состоянию браузера без прямого использования [WebDriverRunner] в тестах.
 */
open class BasePage {

    @Step("Текущий URL страницы")
    fun getUrl(): String = WebDriverRunner.url() ?: ""
}
