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
            Configuration.baseUrl = System.getProperty("selenide.baseUrl", "http://localhost:4000")
            Configuration.timeout = 15_000
            Configuration.pageLoadStrategy = "normal"
            Configuration.reopenBrowserOnFail = true
        }
    }

    @BeforeEach
    fun setup() {
        // No automatic page opening to keep steps explicit in tests
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
