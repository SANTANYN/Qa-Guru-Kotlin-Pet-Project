package frontend.listeners

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import frontend.config.Config
import frontend.helpers.DriverProvider
import io.qameta.allure.Attachment
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

class TestListener : TestExecutionListener {

    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        println("|------ Test Plan started -----|")
        println("Initializing Configuration...").also { ListenerConfig.touch() }
        println("Initializing Selenide WebDriver (browser provider)...").also {
            Configuration.browser = DriverProvider::class.java.name
        }
    }

    override fun executionStarted(testIdentifier: TestIdentifier) {
        if (!testIdentifier.isTest) return
        println("|--- Test started: ${testIdentifier.displayName}")
        val cfg = Config.get()
        if (cfg.verboseLogging) {
            println("[TestConfig] verbose: testId=${testIdentifier.uniqueId}")
        }
    }

    override fun executionFinished(testIdentifier: TestIdentifier, testExecutionResult: TestExecutionResult) {
        if (!testIdentifier.isTest) return

        val cfg = Config.get()
        println("Finished test: ${testIdentifier.displayName} Result: ${testExecutionResult.status}")

        if (testExecutionResult.status == TestExecutionResult.Status.FAILED) {
            if (testIdentifier.displayName != "JUnit Jupiter" && cfg.attachScreenshotsOnFailure) {
                try {
                    if (WebDriverRunner.hasWebDriverStarted()) {
                        attachScreenshot()
                    }
                } catch (e: Exception) {
                    println("[TestListener] Скриншот не сохранён: ${e.message}")
                }
            }
        }
    }

    override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
        if (!testIdentifier.isTest) return
        println("|--- Test Ignored: ${testIdentifier.displayName} - Reason: $reason")
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        try {
            Selenide.closeWebDriver()
        } catch (e: Exception) {
            println("[TestListener] closeWebDriver: ${e.message}")
        }
        println("|------ Test plan is finished -----|")
    }

    /** Как на скрине курса: имя вложения задаётся параметром (Allure подставляет в отчёте). */
    @Attachment(value = "{name}", type = "image/png")
    private fun attachScreenshot(name: String = "SCREENSHOT"): ByteArray? =
        Screenshots.takeScreenShotAsFile()?.readBytes()
}

/** Загрузка [Config.get] и применение baseUrl к JVM / Selenide до старта тестов. */
private object ListenerConfig {
    fun touch() {
        val config = Config.get()
        System.setProperty("selenide.baseUrl", config.baseUrl)
        Configuration.baseUrl = config.baseUrl
        if (config.verboseLogging) {
            println("[TestConfig] baseUrl=${config.baseUrl}, screenshotsOnFailure=${config.attachScreenshotsOnFailure}, verboseLogging=true")
        }
    }
}
