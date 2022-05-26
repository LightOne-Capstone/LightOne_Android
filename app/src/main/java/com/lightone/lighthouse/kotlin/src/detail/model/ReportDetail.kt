package com.lightone.lighthouse.kotlin.src.detail.model

import com.google.gson.annotations.SerializedName

data class ReportDetail(
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

//      {
//            "company_id": "243840",
//            "company_name": "신흥에스이씨",
//            "company_category": "배터리 제조",
//            "date": "2022-05-18",
//            "pdfURL": "https://markets.hankyung.com/pdf/2022/05/cd23c3aaaad14a66094eaf378dddd076",
//            "title": "턴어라운드 시작",
//            "suggestion": "BUY",
//            "keyword": "증설 부품 헝가리 성장 기술 공장 의미 시작 라인 말레이시아 애플 이어폰 배터리 인건비 환경 수기 절대 따라서 보이 회복 비정상 정도 역성 기조 마진 정상화 원통 연초 가동 예정 수주로 입증 상태 공급 소형 원형 코인 전해 누수 방지 장치 미세 장벽 당장 규모 여력 차별 전기차 소재 업체",
//            "summery": "신흥에스이씨에 대해 투자의견 BUY와 목표주가 8만원을 유지하며 비수기라서 절대 매출 수준이 낮았으나 2분기 영업이익률 회복을 기대한다.",
//            "currentPrice": 57300,
//            "targetPrice": 80000,
//            "writerCompany": "유진투자증권",
//            "writer": "한병화"
//        }