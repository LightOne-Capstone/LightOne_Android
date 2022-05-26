package com.lightone.lighthouse.kotlin.src.home.model

import com.lightone.lighthouse.kotlin.src.search.model.Search
import io.reactivex.Single

interface GetReportsDataModel {
    fun getData(): Single<List<Reports>>
}

