package frontend.helpers

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

/**
 * Провайдер локального WebDriver.
 *
 * Поддерживает Chrome (по умолчанию) и Firefox.
 * Выбор браузера через системное свойство `-Dbrowser=firefox`.
 *
 * Пример запуска:
 *   ./gradlew test --tests HomeViewTest
 *   ./gradlew test --tests HomeViewTest -Dbrowser=firefox
 */
open class DriverProvider : WebDriverProvider {

    companion object {
        private val BROWSER_NAME = System.getProperty("browser", "chrome")
    }

    override fun createDriver(capabilities: Capabilities): WebDriver {
        return when (BROWSER_NAME) {
            "chrome" -> {
                val options = ChromeOptions().apply {
                    addArguments("--window-size=1920,1080")
                    addArguments("--disable-gpu")
                    addArguments("--remote-allow-origins=*")
                }
                ChromeDriver(options)
            }

            "firefox" -> {
                val options = FirefoxOptions().apply {
                    addArguments("--width=1920")
                    addArguments("--height=1080")
                }
                FirefoxDriver(options)
            }

            else -> throw IllegalArgumentException("Unsupported browser: $BROWSER_NAME")
        }
    }
}
