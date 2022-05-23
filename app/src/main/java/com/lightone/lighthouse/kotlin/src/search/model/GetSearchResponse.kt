package com.lightone.lighthouse.kotlin.src.search.model

import com.google.gson.annotations.SerializedName

data class GetSearchResponse(
    var searchList: List<Search>
    )