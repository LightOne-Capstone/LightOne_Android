package com.lightone.lighthouse.kotlin.src.searchdetail.model

import io.reactivex.Single

interface SearchDataModel {
    fun getData(searchWord: String): Single<GetSearchResponse>
}

