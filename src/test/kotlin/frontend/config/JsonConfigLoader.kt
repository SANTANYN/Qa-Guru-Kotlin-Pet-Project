package frontend.config

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

private data class JsonConfigDto(
    @SerializedName("selenideBaseUrl")
    val selenideBaseUrl: String? = null,
    @SerializedName("attachScreenshotsOnFailure")
    val attachScreenshotsOnFailure: Boolean? = null,
    @SerializedName("verboseLogging")
    val verboseLogging: Boolean? = null,
)

object JsonConfigLoader {

    private val gson = Gson()

    fun load(classpathPath: String): TestRunConfig {
        val normalized = classpathPath.trim().removePrefix("/")
        val stream = Thread.currentThread().contextClassLoader.getResourceAsStream(normalized)
            ?: JsonConfigLoader::class.java.getResourceAsStream("/$normalized")
            ?: error("Не найден JSON на classpath: $classpathPath")
        val json = stream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        val dto = gson.fromJson(json, JsonConfigDto::class.java)
        return TestRunConfig(
            baseUrl = dto.selenideBaseUrl ?: "http://localhost:4000",
            attachScreenshotsOnFailure = dto.attachScreenshotsOnFailure ?: true,
            verboseLogging = dto.verboseLogging ?: false,
        )
    }
}
