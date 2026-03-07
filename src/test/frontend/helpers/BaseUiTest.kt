package frontend.helpers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseUiTest {

    companion object {
        init {
            Configuration.browser = DriverProvider::class.java.name

            // Для удаленного запуска (Selenoid) используем host.docker.internal,
            // для локального — localhost
            Configuration.baseUrl = if (DriverProvider.isRemote()) {
                System.getProperty("selenide.baseUrl", "http://host.docker.internal:4000")
            } else {
                System.getProperty("selenide.baseUrl", "http://localhost:4000")
            }

            Configuration.timeout = 15_000
            Configuration.pageLoadStrategy = "normal"
            Configuration.reopenBrowserOnFail = true
        }
    }

    @BeforeEach
    fun openBrowser() {
        open("/")
    }

    @AfterEach
    fun closeBrowser() {
        try {
            Selenide.clearBrowserCookies()
            Selenide.clearBrowserLocalStorage()
        } catch (e: Exception) {
            println("Could not clear browser storage: ${e.message}")
        }
    }
}
