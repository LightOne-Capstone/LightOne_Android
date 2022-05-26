package com.lightone.lighthouse.kotlin.src.detail.model

import com.google.gson.annotations.SerializedName
import com.lightone.lighthouse.kotlin.src.search.model.Search

data class GetDetailChartResponse(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("category") var category: String,
    @SerializedName("prices") var prices: List<Chart>,
    @SerializedName("reports") var reports: List<ReportDetail>
    )

// "id": "335870",
//    "name": "IBKS제12호스팩",
//    "category": "금융 지원",
//    "prices"
// "reports": [
//
//    ]