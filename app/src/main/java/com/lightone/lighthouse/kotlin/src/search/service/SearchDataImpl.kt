package com.lightone.lighthouse.kotlin.src.search.service

import com.lightone.lighthouse.kotlin.src.search.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.search.model.SearchDataModel
import io.reactivex.Single

class SearchDataImpl(private val service: SearchAPI) : SearchDataModel {
    override fun getData(searchWord: String): Single<List<Search>> {
        return service.getSearch(searchWord)
    }
}