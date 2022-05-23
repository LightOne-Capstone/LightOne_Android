package com.lightone.lighthouse.kotlin.di


import androidx.room.Room
import com.lightone.lighthouse.kotlin.Database.UserScrapDatabase
import com.lightone.lighthouse.kotlin.Database.UserSearchDatabase
import com.lightone.lighthouse.kotlin.src.home.adapter.DaysAdapter
import com.lightone.lighthouse.kotlin.src.home.adapter.SectorAdapter
import com.lightone.lighthouse.kotlin.src.scrap.adapter.ScrapeAdapter
import com.lightone.lighthouse.kotlin.src.search.adapter.RecentsAdapter
import com.lightone.lighthouse.kotlin.src.search.adapter.SearchAdapter
import com.lightone.lighthouse.kotlin.src.search.model.SearchDataModel
import com.lightone.lighthouse.kotlin.src.search.service.SearchDataImpl
import com.lightone.lighthouse.kotlin.src.suggest.adapter.SuggestAdapter
import com.lightone.lighthouse.kotlin.src.suggest_detail.adapter.SuggestSectorAdapter
import com.lightone.lighthouse.kotlin.viewmodel.*
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * MyModule.kt
 */

var retrofitPart = module {
}

var roomPart = module {
    single {
        Room.databaseBuilder(
            get(),
            UserScrapDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { get<UserScrapDatabase>().userscrapDao() }

    single {
        Room.databaseBuilder(
            get(),
            UserSearchDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { get<UserSearchDatabase>().usersearchDao() }

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
    factory {
        SearchAdapter()
    }
    factory {
        SuggestAdapter()
    }
    factory {
        SuggestSectorAdapter()
    }
    factory {
        ScrapeAdapter()
    }
}

var modelPart = module {
    factory<SearchDataModel> {
        SearchDataImpl(get())
    }
}

var viewModelPart = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { SearchViewModel(get()) }
    viewModel { RecentSearchViewModel(get(), get()) }
    viewModel { DetailViewModel() }
    viewModel { SuggestViewModel() }
    viewModel { SuggestDetailViewModel() }
    viewModel { ScraplViewModel() }
}

var myDiModule = listOf(retrofitPart, roomPart, adapterPart, modelPart, viewModelPart)