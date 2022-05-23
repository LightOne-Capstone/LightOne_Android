package com.lightone.lighthouse.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.searchdetail.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.searchdetail.model.SearchDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchDetailViewModel(private val model: SearchDataModel) : BaseViewModel() {

    private val TAG = "SearchDetailViewModel"

    // get search
    private val _getSearchResponse = MutableLiveData<GetSearchResponse>()
    val getSearchResponse: LiveData<GetSearchResponse>
        get() = _getSearchResponse

    fun getSearch(searchWord: String) {
        addDisposable(model.getData(searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _getSearchResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}