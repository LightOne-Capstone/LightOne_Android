package com.lightone.lighthouse.kotlin.src.suggest.model

import com.google.gson.annotations.SerializedName
import com.lightone.lighthouse.kotlin.src.search.model.Search

data class GetSuggestResponse(
    var searchList: List<Suggests>
    )