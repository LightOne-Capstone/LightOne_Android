package com.lightone.lighthouse.kotlin.src.suggest_detail.model

import com.lightone.lighthouse.kotlin.src.search.model.Search
import io.reactivex.Single

interface GetSuggestDetailDataModel {
    fun getData(categoryName: String, days: String?): Single<List<SuggestDetailResponse>>
}

