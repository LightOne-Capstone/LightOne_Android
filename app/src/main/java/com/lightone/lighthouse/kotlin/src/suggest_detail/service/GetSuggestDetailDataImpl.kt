package com.lightone.lighthouse.kotlin.src.suggest_detail.service

import com.lightone.lighthouse.kotlin.src.detail.model.GetChartDataModel
import com.lightone.lighthouse.kotlin.src.detail.model.GetDetailChartResponse
import com.lightone.lighthouse.kotlin.src.suggest_detail.model.GetSuggestDetailDataModel
import com.lightone.lighthouse.kotlin.src.suggest_detail.model.SuggestDetailResponse
import io.reactivex.Single

class GetSuggestDetailDataImpl(private val service: GetSuggestDetailAPI) : GetSuggestDetailDataModel {
    override fun getData(categoryName: String, days: String?): Single<List<SuggestDetailResponse>> {
        return service.getSuggestDetail(categoryName, days)
    }
}