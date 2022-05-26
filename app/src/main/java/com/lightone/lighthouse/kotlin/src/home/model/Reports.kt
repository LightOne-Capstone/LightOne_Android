package com.lightone.lighthouse.kotlin.src.home.model

import com.google.gson.annotations.SerializedName

data class Reports(
    @SerializedName("company_id") val company_id: String,
    @SerializedName("company_name") val company_name: String,
    @SerializedName("company_category") val company_category: String,
    @SerializedName("date") val date: String,
    @SerializedName("pdfURL") val pdfURL: String,
    @SerializedName("title") val title: String,
    @SerializedName("suggestion") val suggestion: String,
    @SerializedName("keyword") val keyword: String,
    @SerializedName("summery") val summery: String,
    @SerializedName("currentPrice") val currentPrice: Int,
    @SerializedName("targetPrice") val targetPrice: Int,
    @SerializedName("writerCompany") val writerCompany: String,
    @SerializedName("writer") val writer: String
    )
