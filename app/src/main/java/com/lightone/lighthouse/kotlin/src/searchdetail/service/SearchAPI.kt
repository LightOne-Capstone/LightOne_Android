package com.lightone.lighthouse.kotlin.src.searchdetail.service

import com.lightone.lighthouse.kotlin.src.searchdetail.model.GetSearchResponse
import io.reactivex.Single
import retrofit2.http.*

interface SearchAPI {
    @GET("/company/search/{search_keyboard}")
    fun getSearch(@Path("search_keyboard") search_keyboard: String) : Single<GetSearchResponse>
}