package com.lightone.lighthouse.kotlin.src.suggest_detail.model

import com.google.gson.annotations.SerializedName
import com.lightone.lighthouse.kotlin.src.detail.model.ReportDetail
import com.lightone.lighthouse.kotlin.src.search.model.Search

data class SuggestDetailResponse(
    @SerializedName("company_id") var company_id: String,
    @SerializedName("company_name") var company_name: String,
    @SerializedName("company_category") var company_category: String,
    @SerializedName("date") var date: String,
    @SerializedName("pdfURL") var pdfURL: String,
    @SerializedName("title") var title: String,
    @SerializedName("suggestion") var suggestion: String,
    @SerializedName("keyword") var keyword: String,
    @SerializedName("summery") var summery: String,
    @SerializedName("currentPrice") var currentPrice: Int,
    @SerializedName("targetPrice") var targetPrice: Int,
    @SerializedName("writerCompany") var writerCompany: String,
    @SerializedName("writer") var writer: String
    )

//{
//    "company_id": "082210",
//    "company_name": "옵트론텍",
//    "company_category": "광학기기 제조",
//    "date": "2022-05-18",
//    "pdfURL": "https://markets.hankyung.com/pdf/2022/05/f66ca4567a42de7055c0a86ef33555ca",
//    "title": "1Q 부진, 3Q 회복",
//    "suggestion": "BUY",
//    "keyword": "생산 성장 광학 하반기 필터 마진 고객 저가 중심 국내 진출 보정 부품 다각 추진 업체 기회 적지 연속 적자 설비 베트남 이전 일부 차질 경쟁심 화로 정체 하락 성은 사의 스마트폰 제한 포인트 전장 토대 구축 신규 전자 라인업 갤럭시 시리즈 채택 측면 형태 변화 후발 주자 부여 안정",
//    "summery": "삼성과 스마트폰의 중저가 라인업 확대로 인해 수익성은 전분기대비 개선될 전망이다.",
//    "currentPrice": 6240,
//    "targetPrice": 10000,
//    "writerCompany": "대신증권",
//    "writer": "박강호"
//}