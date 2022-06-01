package com.lightone.lighthouse.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lightone.lighthouse.kotlin.Database.dao.UserScrapDao
import com.lightone.lighthouse.kotlin.Database.model.UserScrap
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.suggest_detail.model.GetSuggestDetailDataModel
import com.lightone.lighthouse.kotlin.src.suggest_detail.model.SuggestDetailResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuggestDetailViewModel(
    private val model: GetSuggestDetailDataModel,
    private val dao: UserScrapDao
    ) : BaseViewModel() {

    private val TAG = "SuggestDetailViewModel"

    private val _suggestDetailResponse = MutableLiveData<List<SuggestDetailResponse>>()
    val suggestDetailResponse: LiveData<List<SuggestDetailResponse>>
        get() = _suggestDetailResponse

    fun suggestDetail(categoryName: String, days: String?) {
        addDisposable(model.getData(categoryName, days)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _suggestDetailResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
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