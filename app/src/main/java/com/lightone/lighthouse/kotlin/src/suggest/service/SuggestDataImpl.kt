package com.lightone.lighthouse.kotlin.src.suggest.service

import com.lightone.lighthouse.kotlin.src.search.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.search.model.SearchDataModel
import com.lightone.lighthouse.kotlin.src.suggest.model.SuggestDataModel
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import io.reactivex.Single

class SuggestDataImpl(private val service: SuggestAPI) : SuggestDataModel {
    override fun getData(days: String): Single<List<Suggests>> {
        return service.getSuggest(days)
    }
}