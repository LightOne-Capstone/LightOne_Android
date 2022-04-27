package com.lightone.android.kotlin.config

import android.content.SharedPreferences
import retrofit2.Retrofit

class MyConstant {
    // 코틀린의 전역변수 문법
    companion object {
        const val BASE_URL = "http://server.shop:8080"
        const val radius = 16
    }
}