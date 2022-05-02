package com.lightone.lighthouse.kotlin.di


import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.src.home.adapter.SectorAdapter
import com.lightone.lighthouse.kotlin.src.search.adapter.RecentsAdapter
import com.lightone.lighthouse.kotlin.viewmodel.*
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import java.util.*

/**
 * MyModule.kt
 */

var retrofitPart = module {
}

var adapterPart = module {
    factory {
        DaysAdapter()
    }
    factory {
        SectorAdapter()
    }
    factory {
        RecentsAdapter()
    }
}

var modelPart = module {
//    factory<DataModel> {
//        DataModelImpl(get())
//    }
}

var viewModelPart = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { SearchViewModel() }
    viewModel { DetailViewModel() }
    viewModel { SuggestViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)