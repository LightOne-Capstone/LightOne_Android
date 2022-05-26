package com.lightone.lighthouse.kotlin.src.detail.service

import com.lightone.lighthouse.kotlin.src.detail.model.GetDetailChartResponse
import com.lightone.lighthouse.kotlin.src.search.model.Search
import io.reactivex.Single
import retrofit2.http.*

interface GetDetailChartAPI {
    @GET("/company/id/{idx}")
    fun getSearch(@Path("idx") companyId: String) : Single<GetDetailChartResponse>
}