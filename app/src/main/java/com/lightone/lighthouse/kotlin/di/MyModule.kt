package com.lightone.lighthouse.kotlin.di


import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.viewmodel.HomeViewModel
import com.lightone.lighthouse.kotlin.viewmodel.MainViewModel
import com.lightone.lighthouse.kotlin.viewmodel.SearchViewModel
import com.lightone.lighthouse.kotlin.viewmodel.SuggestViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * MyModule.kt
 */

var retrofitPart = module {
}

var adapterPart = module {
    factory {
        DaysAdapter()
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
    viewModel { SuggestViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)