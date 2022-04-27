package com.lightone.lighthouse.kotlin.di


import com.lightone.lighthouse.kotlin.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * MyModule.kt
 */

var retrofitPart = module {
}

var adapterPart = module {
//    factory {
//        UserQuestionListAdapter()
//    }
}

var modelPart = module {
//    factory<DataModel> {
//        DataModelImpl(get())
//    }
}

var viewModelPart = module {
    viewModel { MainViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)