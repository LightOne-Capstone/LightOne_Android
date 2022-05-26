package com.lightone.lighthouse.kotlin.src.home.service

import com.lightone.lighthouse.kotlin.src.home.model.Reports
import com.lightone.lighthouse.kotlin.src.search.model.Search
import io.reactivex.Single
import retrofit2.http.*

interface ReportAPI {
    @GET("/report/")
    fun getReports() : Single<List<Reports>>
}