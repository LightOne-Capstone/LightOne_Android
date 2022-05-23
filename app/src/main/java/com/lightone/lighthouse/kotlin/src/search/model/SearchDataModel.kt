package com.lightone.lighthouse.kotlin.src.search.model

import io.reactivex.Single

interface SearchDataModel {
    fun getData(searchWord: String): Single<List<Search>>
}

