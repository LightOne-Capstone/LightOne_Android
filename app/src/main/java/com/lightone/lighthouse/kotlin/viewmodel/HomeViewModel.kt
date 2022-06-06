package com.lightone.lighthouse.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lightone.lighthouse.kotlin.Database.dao.UserScrapDao
import com.lightone.lighthouse.kotlin.Database.model.UserScrap
import com.lightone.lighthouse.kotlin.Database.model.UserSearch
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.detail.model.GetChartDataModel
import com.lightone.lighthouse.kotlin.src.detail.model.GetDetailChartResponse
import com.lightone.lighthouse.kotlin.src.home.model.GetReportsDataModel
import com.lightone.lighthouse.kotlin.src.home.model.Reports
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val model: GetReportsDataModel,
                    private val dao: UserScrapDao) : BaseViewModel() {

    private val TAG = "HomeViewModel"

    private val _reportResponse = MutableLiveData<List<Reports>>()
    val reportdResponse: LiveData<List<Reports>>
        get() = _reportResponse

    fun getReportList() {
        addDisposable(model.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _reportResponse.postValue(this)
                }
            }, {
                getReportList()
            })
        )
    }

    private val _scrapResponse = MutableLiveData<Boolean>()
    val scrapResponse: LiveData<Boolean>
        get() = _scrapResponse

    fun insertSearch(userScrap: UserScrap) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(userScrap).let {
                _scrapResponse.postValue(true)
            }
        }
    }
}