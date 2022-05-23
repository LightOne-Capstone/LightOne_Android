package com.lightone.lighthouse.kotlin.src.searchdetail.service

import com.lightone.lighthouse.kotlin.src.searchdetail.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.searchdetail.model.SearchDataModel
import io.reactivex.Single

class SearchDataImpl(private val service: SearchAPI) : SearchDataModel {
    override fun getData(searchWord: String): Single<GetSearchResponse> {
        return service.getSearch(searchWord)
    }
}