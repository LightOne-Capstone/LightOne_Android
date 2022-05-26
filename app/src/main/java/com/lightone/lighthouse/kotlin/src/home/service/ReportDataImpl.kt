package com.lightone.lighthouse.kotlin.src.home.service

import com.lightone.lighthouse.kotlin.src.home.model.GetReportsDataModel
import com.lightone.lighthouse.kotlin.src.home.model.Reports
import com.lightone.lighthouse.kotlin.src.search.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.search.model.SearchDataModel
import io.reactivex.Single

class ReportDataImpl(private val service: ReportAPI) : GetReportsDataModel {
    override fun getData(): Single<List<Reports>> {
        return service.getReports()
    }
}