package frontend.config

/**
 * Тонкая обёртка как `Config.get` на занятиях (скрин TestListener): делегирует в [TestConfig].
 */
object Config {
    fun get(): TestRunConfig = TestConfig.get()
}
