package com.lightone.lighthouse.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lightone.lighthouse.kotlin.Database.dao.SearchDao
import com.lightone.lighthouse.kotlin.Database.model.UserSearch
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.search.model.GetSearchResponse
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.search.model.SearchDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecentSearchViewModel(private val dao: SearchDao,
                            private val model: SearchDataModel) : BaseViewModel() {

    private val TAG = "RecentSearchViewModel"

    fun getRecentList(): LiveData<List<UserSearch>> = dao.getAll()

    private val _searchResponse = MutableLiveData<List<Search>>()
    val searchResponse: LiveData<List<Search>>
        get() = _searchResponse

    fun search(searchWord: String) {
        addDisposable(model.getData(searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _searchResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    private val _deletesuccessResponse = MutableLiveData<Boolean>()
    val deletesuccessResponse: LiveData<Boolean>
        get() = _deletesuccessResponse

    fun delete(contents: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteScrap(contents).let {
                _deletesuccessResponse.postValue(true)
            }
        }
    }
}