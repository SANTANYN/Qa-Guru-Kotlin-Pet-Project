package frontend.listeners

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Attachment
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot

class TestListener : TestExecutionListener {

    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        println("Test Plan Started")
        println("Initializing Configurations...")
        ListenerConfig.touch()
    }

    override fun executionFinished(testIdentifier: TestIdentifier, testExecutionResult: TestExecutionResult) {
        if (!testIdentifier.isTest) return

        println("Test: ${testIdentifier.displayName} — ${testExecutionResult.status}")

        if (testExecutionResult.status == TestExecutionResult.Status.FAILED) {
            val name = testIdentifier.displayName
            if (!name.contains("JUnit", ignoreCase = true)) {
                try {
                    if (WebDriverRunner.hasWebDriverStarted()) {
                        val driver = WebDriverRunner.getWebDriver()
                        if (driver is TakesScreenshot) {
                            val bytes = driver.getScreenshotAs(OutputType.BYTES)
                            if (bytes.isNotEmpty()) {
                                attachScreenshot(bytes)
                            }
                        }
                    }
                } catch (e: Exception) {
                    println("[TestListener] Скриншот не сохранён: ${e.message}")
                }
            }
        }
    }

    override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
        if (testIdentifier.isTest) {
            println("Test IGNORED: ${testIdentifier.displayName}, reason: $reason")
        }
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        println("Test Plan Finished")
        try {
            Selenide.closeWebDriver()
        } catch (e: Exception) {
            println("[TestListener] closeWebDriver: ${e.message}")
        }
    }

    @Attachment(value = "SCREENSHOT", type = "image/png")
    private fun attachScreenshot(screenshot: ByteArray): ByteArray = screenshot
}

/** Заглушка под Config.get с занятий — подключи сюда загрузку properties/json. */
private object ListenerConfig {
    fun touch() {
        // Например: Config.get или чтение System.getProperty(...)
    }
}
