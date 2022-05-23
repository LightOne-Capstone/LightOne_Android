package com.lightone.lighthouse.kotlin.src.searchdetail.model

import com.google.gson.annotations.SerializedName

data class GetSearchResponse(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("category") var category: String,
    @SerializedName("prices") var prices: List<GetSearch>
    )

// "id": "323410",
//    "name": "카카오뱅크",
//    "category": "은행",
//    "prices"