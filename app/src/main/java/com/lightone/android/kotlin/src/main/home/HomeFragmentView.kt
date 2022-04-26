package com.lightone.android.kotlin.src.main.home

import com.lightone.android.kotlin.src.main.home.models.SignUpResponse
import com.lightone.android.kotlin.src.main.home.models.UserResponse

interface HomeFragmentView {

    fun onGetUserSuccess(response: UserResponse)

    fun onGetUserFailure(message: String)

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}