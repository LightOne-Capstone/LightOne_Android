package com.lightone.lighthouse.kotlin.src.search.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String
    )
