package backend.controllers

import backend.api.ApiGson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class Controllers {

    private val apiRoot: String
        get() {
            val base = System.getProperty("api.base.url", "http://localhost:1111").trimEnd('/')
            return "$base/api/v1/"
        }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(apiRoot)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(ApiGson.gson))
            .build()
    }

    protected val auth by lazy { AuthController(retrofit) }
    protected val users by lazy { UsersController(retrofit) }
}
