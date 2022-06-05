package com.lightone.lighthouse.kotlin.util

import com.lightone.lighthouse.kotlin.R
import java.text.DecimalFormat

fun priceFormatter(item: Int): String{
    val myFormatter = DecimalFormat("###,###")
    return myFormatter.format(item)
}

fun suggestImg(name: String): Int {
    var img: Int? = null
    var category = industryImg(name)
    when(category){
        "예술 & 체육" -> img = R.drawable.ic_moive
        "식품" -> img = R.mipmap.ic_food_foreground
        "금속" -> img = R.mipmap.ic_steel_foreground
        "제작 & 제조" -> img = R.mipmap.ic_industry_foreground
        "운송" -> img = R.drawable.ic_airplane
        "건설" -> img = R.mipmap.ic_constructor_foreground
        "과학 & 기술" -> img = R.mipmap.ic_chemical_foreground
        "생활" -> img = R.mipmap.ic_life_foreground
        "전자 & 컴퓨터" -> img = R.mipmap.ic_computer_foreground
        "서비스" -> img = R.mipmap.ic_service_foreground
        "금융 & 부동산" -> img = R.mipmap.ic_bank_foreground
        "교욱" -> img = R.mipmap.ic_edu_foreground
        "전기" -> img = R.mipmap.ic_electronic_foreground
        "화학" -> img = R.drawable.ic_medical
        "자동차" -> img = R.drawable.ic_automobile
        "판매" -> img = R.mipmap.ic_sale_foreground
        else -> img = R.mipmap.ic_default_report_foreground
    }
    return img
}

fun industryImg(category: String): String{
//    예술 & 체육
//    골프, 악기, 음반, 디자인, 예술, 스포츠
    if(category.contains("골프") || category.contains("악기") || category.contains("음반") ||
        category.contains("디자인") || category.contains("예술") || category.contains("스포츠")) {
            return "예술 & 체육"
    }

//    식품
//    채소, 곡물, 음료, 담배, 식품, 사료, 어업, 작물, 유제품, 음식점, 육류
    else if(category.contains("채소") || category.contains("곡물") || category.contains("음료") ||
        category.contains("담배") || category.contains("식품") || category.contains("사료") ||
        category.contains("어업") || category.contains("작물") || category.contains("유제품") ||
        category.contains("음식점") || category.contains("육류")) {
        return "식품"
    }

//    금속
//    금속, 철강
    else if(category.contains("골프") || category.contains("철강")) {
        return "금속"
    }

//    제작 & 제조
//    제조, 인쇄, 제작
    else if(category.contains("제조") || category.contains("인쇄") || category.contains("제작")) {
        return "제작 & 제조"
    }

//    운송
//    운송
    else if(category.contains("운송")) {
        return "운송"
    }

//    건설
//    건물, 건설, 건축, 시설물, 조명
    else if(category.contains("건설") || category.contains("건물") || category.contains("건축") ||
        category.contains("시설물") || category.contains("조명")) {
        return "건설"
    }

//    과학 & 기술
//    과학, 기술, 연구, 개발
    else if(category.contains("과학") || category.contains("기술") || category.contains("연구")) {
        return "과학 & 기술"
    }

//    의복 & 악세사리
//    신발, 가죽, 가방, 귀금속, 섬유, 의료, 의복, 악세사리, 원단, 직물
    else if(category.contains("신발") || category.contains("가죽") || category.contains("가방") ||
        category.contains("귀금속") || category.contains("섬유") || category.contains("의료") ||
        category.contains("의복") || category.contains("악세사리") || category.contains("원단") ||
        category.contains("직물")) {
        return "의복 & 악세사리"
    }

//    생활
//    가정용, 냉-온수
    else if(category.contains("가정용") || category.contains("냉-온수")) {
        return "생활"
    }

//    전자 & 컴퓨터
//    소프트웨어, 가전제품, 통신, 컴퓨터, 기록매체, 반도체, 전자부품, 영상
    else if(category.contains("소프트웨어") || category.contains("가전제품") || category.contains("통신") ||
        category.contains("컴퓨터") || category.contains("기록매체") || category.contains("반도체") ||
        category.contains("전자부품") || category.contains("영상")) {
        return "전자 & 컴퓨터"
    }

//    서비스
//    서비스, 경비, 경호, 광고, 방송, 숙박시설, 시장조사, 보험, 여행사, 오락, 실내 건축, 환경, 유지, 컨설팅, 중계
    else if(category.contains("서비스") || category.contains("경비") || category.contains("경호") ||
        category.contains("광고") || category.contains("방송") || category.contains("숙박시설") ||
        category.contains("시장조사") || category.contains("보험") || category.contains("여행사") ||
        category.contains("오락") || category.contains("실내 건축") || category.contains("환경") ||
        category.contains("유지") || category.contains("컨설팅") || category.contains("중계")) {
        return "서비스"
    }

//    금용 & 부동산
//    금융, 은행, 신탁, 부동산
    else if(category.contains("금융") || category.contains("은행") || category.contains("신탁") ||
        category.contains("부동산")) {
        return "금용 & 부동산"
    }

//    교육
//    학원, 교욱
    else if(category.contains("학원") || category.contains("교욱")) {
        return "교육"
    }

//    전기
//    전기
    else if(category.contains("전기") || category.contains("전자")) {
        return "전기"
    }

//    화학
//    화학, 플라스틱, 베터리, 석유 정제품, 시멘트, 석회, 비금속 광물, 비금속광물, 의약품, 고무제품, 연료용 가스, 석탄
    else if(category.contains("화학") || category.contains("플라스틱") || category.contains("석유 정제품") ||
        category.contains("시멘트") || category.contains("석회") || category.contains("비금속 광물") ||
        category.contains("비금속광물") || category.contains("의약품") || category.contains("고무제품") ||
        category.contains("연료용 가스") || category.contains("석탄")) {
        return "화학"
    }

//    자동차
//    자동차, 엔진
    else if(category.contains("자동차") || category.contains("엔진")) {
        return "자동차"
    }

//    판매
//    도매
    else if(category.contains("판매") || category.contains("도매")) {
        return "판매"
    }
    return "null"
}