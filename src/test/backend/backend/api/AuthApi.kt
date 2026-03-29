package backend.api

import backend.api.models.AuthLoginRequest
import backend.api.models.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    fun login(@Body body: AuthLoginRequest): Call<AuthResponse>
}
