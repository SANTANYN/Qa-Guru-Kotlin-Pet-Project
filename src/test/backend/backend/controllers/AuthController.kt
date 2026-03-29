package backend.controllers

import backend.api.AuthApi
import backend.api.models.AuthLoginRequest
import backend.api.models.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit

class AuthController(retrofit: Retrofit) {

    private val api = retrofit.create(AuthApi::class.java)

    fun login(email: String, password: String): Response<AuthResponse> =
        api.login(AuthLoginRequest(email, password)).execute()
}
