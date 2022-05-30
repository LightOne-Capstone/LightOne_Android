package com.lightone.lighthouse.kotlin.src.suggest.service

import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import io.reactivex.Single
import retrofit2.http.*

interface SuggestAPI {
    @GET("/report/category")
    fun getSuggest(@Query("days") days: String) : Single<List<Suggests>>
}