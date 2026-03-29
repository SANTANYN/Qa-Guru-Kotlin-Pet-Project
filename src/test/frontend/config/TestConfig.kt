package frontend.config

/**
 * Синглтон конфигурации: читает путь из [System.getProperty], парсит JSON или properties.
 *
 * Приоритет: `frontend.test.json` → `frontend.test.properties` → `env_config` → дефолтный `.properties` в resources.
 * Передача из Gradle/CLI: `./gradlew test -Dfrontend.test.json=/config/frontend-test.json`
 */
object TestConfig {

    @Volatile
    private var cached: TestRunConfig? = null

    /** Сброс кэша (для демо-тестов, меняющих System property между кейсами). */
    @Synchronized
    fun invalidate() {
        cached = null
    }

    /**
     * Загрузка с учётом кэша (один раз на прогон, пока не вызван [invalidate]).
     * Аналог `Config.get` с занятий.
     */
    fun get(): TestRunConfig = cached ?: synchronized(this) {
        cached ?: loadFromSources().also { cached = it }
    }

    fun loadFromSources(): TestRunConfig {
        val jsonPath = System.getProperty(TestConfigKeys.JSON_PATH)?.trim().orEmpty()
        val propsPath = System.getProperty(TestConfigKeys.PROPERTIES_PATH)?.trim().orEmpty()
        val envConfigPath = System.getProperty(TestConfigKeys.ENV_CONFIG)?.trim().orEmpty()
        return when {
            jsonPath.isNotEmpty() -> JsonConfigLoader.load(jsonPath)
            propsPath.isNotEmpty() -> PropertiesConfigLoader.load(propsPath)
            envConfigPath.isNotEmpty() -> PropertiesConfigLoader.load(envConfigPath)
            else -> PropertiesConfigLoader.load(TestConfigKeys.DEFAULT_PROPERTIES_RESOURCE)
        }
    }
}
