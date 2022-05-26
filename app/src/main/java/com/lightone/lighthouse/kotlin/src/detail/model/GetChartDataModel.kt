package com.lightone.lighthouse.kotlin.src.detail.model

import com.lightone.lighthouse.kotlin.src.search.model.Search
import io.reactivex.Single

interface GetChartDataModel {
    fun getData(companyId: String): Single<GetDetailChartResponse>
}

