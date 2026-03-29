package backend.api

import backend.api.models.users.CreateUserRequest
import backend.api.models.users.SuccessBody
import backend.api.models.users.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersApi {

    @POST("users/create")
    fun createUser(@Body body: CreateUserRequest): Call<UserResponse>

    @GET("users/{id}")
    fun getUserById(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int,
    ): Call<UserResponse>

    @DELETE("users/{id}")
    fun deleteUser(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int,
    ): Call<SuccessBody>
}
