package com.lightone.lighthouse.kotlin.src.detail.service

import com.lightone.lighthouse.kotlin.src.detail.model.GetChartDataModel
import com.lightone.lighthouse.kotlin.src.detail.model.GetDetailChartResponse
import com.lightone.lighthouse.kotlin.src.search.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.search.model.SearchDataModel
import io.reactivex.Single

class GetDetailChartDataImpl(private val service: GetDetailChartAPI) : GetChartDataModel {
    override fun getData(Idx: String): Single<GetDetailChartResponse> {
        return service.getSearch(Idx)
    }
}