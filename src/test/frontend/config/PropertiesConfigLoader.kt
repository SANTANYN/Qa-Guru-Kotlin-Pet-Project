package frontend.config

import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Properties

object PropertiesConfigLoader {

    fun load(classpathPath: String): TestRunConfig {
        val normalized = classpathPath.trim().removePrefix("/")
        val stream = Thread.currentThread().contextClassLoader.getResourceAsStream(normalized)
            ?: PropertiesConfigLoader::class.java.getResourceAsStream("/$normalized")
            ?: error("Не найден ресурс на classpath: $classpathPath (пробовали: $normalized)")
        return InputStreamReader(stream, StandardCharsets.UTF_8).use { reader ->
            Properties().apply { load(reader) }.toTestRunConfig()
        }
    }

    private fun Properties.toTestRunConfig(): TestRunConfig {
        val baseUrl = getProperty("selenide.baseUrl")
            ?: getProperty("frontend.baseUrl")
            ?: "http://localhost:4000"
        val screenshots = getProperty("screenshots.on.failure", "true").equals("true", ignoreCase = true)
        val verbose = getProperty("verbose.logging", "false").equals("true", ignoreCase = true)
        return TestRunConfig(
            baseUrl = baseUrl,
            attachScreenshotsOnFailure = screenshots,
            verboseLogging = verbose,
        )
    }
}
