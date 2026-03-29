package backend.api.extension

import backend.api.ApiGson
import retrofit2.Response

fun <T> Response<T>.getAsObject(): T {
    check(isSuccessful) {
        "Expected success but was ${code()}: ${errorBody()?.string()}"
    }
    val body = body()
    requireNotNull(body) { "Success response body is null" }
    return body
}

inline fun <reified T : Any> Response<*>.getErrorAsObject(): T {
    check(!isSuccessful) { "Expected error response but was successful: ${code()}" }
    val raw = errorBody()?.string().orEmpty()
    require(raw.isNotEmpty()) { "Error body is empty (HTTP ${code()})" }
    return ApiGson.gson.fromJson(raw, T::class.java)
        ?: error("Failed to parse error body as ${T::class.simpleName}: $raw")
}
