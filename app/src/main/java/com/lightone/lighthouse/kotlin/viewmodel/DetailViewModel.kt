package com.lightone.lighthouse.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.detail.model.GetChartDataModel
import com.lightone.lighthouse.kotlin.src.detail.model.GetDetailChartResponse
import com.lightone.lighthouse.kotlin.src.search.model.Search
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val model: GetChartDataModel) : BaseViewModel() {

    private val TAG = "DetailViewModel"

    private val _detailchartResponse = MutableLiveData<GetDetailChartResponse>()
    val detailchartResponse: LiveData<GetDetailChartResponse>
        get() = _detailchartResponse

    fun detailChart(searchWord: String) {
        addDisposable(model.getData(searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _detailchartResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}