package com.lightone.lighthouse.kotlin.src.suggest_detail.service

import com.lightone.lighthouse.kotlin.src.detail.model.GetDetailChartResponse
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.suggest_detail.model.SuggestDetailResponse
import io.reactivex.Single
import retrofit2.http.*

interface GetSuggestDetailAPI {
    @GET("/report/category/{categoryName}")
    fun getSuggestDetail(
        @Path("categoryName") categoryName: String,
        @Query("days") days: String?
    ) : Single<List<SuggestDetailResponse>>
}