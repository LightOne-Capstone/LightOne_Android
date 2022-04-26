package com.lightone.android.kotlin.src.main.home

import com.lightone.android.kotlin.src.main.home.models.PostSignUpRequest
import com.lightone.android.kotlin.src.main.home.models.SignUpResponse
import com.lightone.android.kotlin.src.main.home.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {
    @GET("/users")
    fun getUsers() : Call<UserResponse>

    @POST("/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>
}
