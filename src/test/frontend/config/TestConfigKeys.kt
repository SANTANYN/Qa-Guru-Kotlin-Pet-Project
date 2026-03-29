package frontend.config

/**
 * Ключи [System.getProperty]: путь к файлу на classpath (как `/config/frontend-test.properties`).
 */
object TestConfigKeys {
    const val PROPERTIES_PATH = "frontend.test.properties"
    const val JSON_PATH = "frontend.test.json"

    /** Как на курсе: путь к `.properties` на classpath (`/example.properties`). */
    const val ENV_CONFIG = "env_config"

    /** Если оба ключа не заданы — этот ресурс по умолчанию. */
    const val DEFAULT_PROPERTIES_RESOURCE = "/config/frontend-test.properties"
}
