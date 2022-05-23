package com.lightone.lighthouse.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lightone.lighthouse.kotlin.Database.dao.SearchDao
import com.lightone.lighthouse.kotlin.Database.model.Search
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.searchdetail.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.searchdetail.model.SearchDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val dao: SearchDao,
                      private val model: SearchDataModel
) : BaseViewModel() {

    private val TAG = "SearchViewModel"

    private val _insertsuccessResponse = MutableLiveData<Boolean>()
    val insertsuccessResponse: LiveData<Boolean>
        get() = _insertsuccessResponse

    fun search(search: Search) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(search).let {
                _insertsuccessResponse.postValue(true)
            }
        }
    }
}