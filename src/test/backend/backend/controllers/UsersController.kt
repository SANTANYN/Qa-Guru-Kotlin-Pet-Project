package backend.controllers

import backend.api.UsersApi
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.SuccessBody
import backend.api.models.users.UserResponse
import retrofit2.Response
import retrofit2.Retrofit

class UsersController(retrofit: Retrofit) {

    private val api = retrofit.create(UsersApi::class.java)

    fun createUser(request: CreateUserRequest): Response<UserResponse> =
        api.createUser(request).execute()

    fun getUserById(accessToken: String, id: Int): Response<UserResponse> =
        api.getUserById(bearer(accessToken), id).execute()

    fun deleteUser(accessToken: String, id: Int): Response<SuccessBody> =
        api.deleteUser(bearer(accessToken), id).execute()

    private fun bearer(accessToken: String) = "Bearer $accessToken"
}
