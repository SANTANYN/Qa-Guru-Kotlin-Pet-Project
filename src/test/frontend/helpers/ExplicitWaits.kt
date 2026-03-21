package frontend.helpers

import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

/**
 * Явные ожидания на уровне Selenium API (не Selenide-conditions на элементах).
 * Таймаут и условие видны в коде и в стектрейсе при таймауте.
 */
object ExplicitWaits {

    /**
     * Ждёт, пока у элемента [locator] текст станет в точности [expectedText].
     */
    fun waitForExactText(
        locator: By,
        expectedText: String,
        timeout: Duration = Duration.ofSeconds(15),
    ) {
        val driver = WebDriverRunner.getWebDriver()
        WebDriverWait(driver, timeout).until(ExpectedConditions.textToBe(locator, expectedText))
    }
}
