package frontend.config

/**
 * Общая модель настроек тестового прогона (properties и JSON мапятся сюда).
 */
data class TestRunConfig(
    val baseUrl: String,
    val attachScreenshotsOnFailure: Boolean = true,
    val verboseLogging: Boolean = false,
)
