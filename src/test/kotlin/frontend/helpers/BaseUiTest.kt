package frontend.helpers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseUiTest {

    companion object {
        init {
            // Browser и baseUrl также выставляет [frontend.listeners.TestListener] при старте плана;
            // здесь — fallback, если класс загрузился до listener или для тестов без SPI.
            Configuration.browser = DriverProvider::class.java.name
            Configuration.baseUrl = System.getProperty("selenide.baseUrl", "http://localhost:4000")
            Configuration.timeout = 15_000
            Configuration.pageLoadStrategy = "normal"
            Configuration.reopenBrowserOnFail = true
        }
    }

    @BeforeEach
    fun setup() {
        // Подтянуть baseUrl после TestListener / демо-тестов, меняющих System properties.
        Configuration.baseUrl = System.getProperty("selenide.baseUrl", Configuration.baseUrl)
        Configuration.browser = DriverProvider::class.java.name
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
