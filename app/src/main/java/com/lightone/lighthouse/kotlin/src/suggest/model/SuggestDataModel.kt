package com.lightone.lighthouse.kotlin.src.suggest.model

import com.lightone.lighthouse.kotlin.src.search.model.Search
import io.reactivex.Single

interface SuggestDataModel {
    fun getData(days: String): Single<List<Suggests>>
}

