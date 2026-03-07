package frontend.helpers

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.LocalFileDetector
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI

/**
 * Провайдер WebDriver с гибкой настройкой:
 *
 * - По умолчанию запускает **локальный** браузер (ChromeDriver/FirefoxDriver).
 *   Это быстрее и не требует Docker/Selenoid.
 *
 * - Если передан `-Dremote.url=http://...`, используется **RemoteWebDriver**
 *   (Selenoid, Selenium Grid, Moon и т.д.).
 *
 * Примеры запуска:
 *   Локально:    ./gradlew test --tests HomeViewTest
 *   Selenoid:    ./gradlew test --tests HomeViewTest -Dremote.url=http://localhost:4444/wd/hub
 *   Удалённый:   ./gradlew test --tests HomeViewTest -Dremote.url=https://user:pass@selenoid.cloud/wd/hub
 */
open class DriverProvider : WebDriverProvider {

    companion object {
        private val BROWSER_NAME = System.getProperty("browser", "chrome")
        private val REMOTE_URL: String? = System.getProperty("remote.url")

        fun isRemote(): Boolean = !REMOTE_URL.isNullOrBlank()
    }

    override fun createDriver(capabilities: Capabilities): WebDriver {
        return if (isRemote()) {
            createRemoteDriver()
        } else {
            createLocalDriver()
        }
    }

    private fun createLocalDriver(): WebDriver {
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

    private fun createRemoteDriver(): WebDriver {
        val options = when (BROWSER_NAME) {
            "chrome" -> ChromeOptions().apply {
                setCapability("browserVersion", "128.0")
                setCapability(
                    "selenoid:options",
                    mapOf(
                        "enableVNC" to true,
                        "screenResolution" to "1920x1080"
                    )
                )
                addArguments("--window-size=1920,1080")
                addArguments("--disable-gpu")
                addArguments("--remote-allow-origins=*")
            }

            "firefox" -> FirefoxOptions().apply {
                setCapability("browserVersion", "125.0")
                setCapability(
                    "selenoid:options",
                    mapOf(
                        "enableVNC" to true,
                        "screenResolution" to "1920x1080"
                    )
                )
                addArguments("--width=1920")
                addArguments("--height=1080")
            }

            else -> throw IllegalArgumentException("Unsupported browser: $BROWSER_NAME")
        }

        return RemoteWebDriver(URI(REMOTE_URL!!).toURL(), options).apply {
            fileDetector = LocalFileDetector()
        }
    }
}
