package frontend

import frontend.config.Config
import frontend.config.TestConfig
import frontend.config.TestConfigKeys
import frontend.listeners.TestWatcherExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@Epic("Frontend Tests")
@Feature("Config and TestWatcher demo")
@ExtendWith(TestWatcherExtension::class)
class ConfigAndWatcherDemoTest {

    @AfterEach
    fun resetConfigSystemProperties() {
        System.clearProperty(TestConfigKeys.JSON_PATH)
        System.clearProperty(TestConfigKeys.PROPERTIES_PATH)
        System.clearProperty(TestConfigKeys.ENV_CONFIG)
        TestConfig.invalidate()
    }

    @Test
    @DisplayName("Positive test: read and check frontend-test.properties via env_config")
    fun testPropertiesLoading() {
        TestConfig.invalidate()
        System.clearProperty(TestConfigKeys.JSON_PATH)
        System.clearProperty(TestConfigKeys.PROPERTIES_PATH)
        System.setProperty(TestConfigKeys.ENV_CONFIG, "/config/frontend-test.properties")

        val props = Config.get()
        println("Properties file: $props")
        props.baseUrl shouldStartWith "http"
        props.attachScreenshotsOnFailure shouldBe true
    }
}
