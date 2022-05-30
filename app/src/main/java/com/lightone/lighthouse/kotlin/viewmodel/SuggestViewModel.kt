package com.lightone.lighthouse.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.detail.model.GetDetailChartResponse
import com.lightone.lighthouse.kotlin.src.suggest.model.SuggestDataModel
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SuggestViewModel(private val model: SuggestDataModel) : BaseViewModel() {

    private val TAG = "SuggestViewModel"

    private val _suggestResponse = MutableLiveData<List<Suggests>>()
    val suggestResponse: LiveData<List<Suggests>>
        get() = _suggestResponse

    fun getSuggest(days: String) {
        addDisposable(model.getData(days)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _suggestResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}