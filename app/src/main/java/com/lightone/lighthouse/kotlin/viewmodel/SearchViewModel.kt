package com.lightone.lighthouse.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lightone.lighthouse.kotlin.Database.dao.SearchDao
import com.lightone.lighthouse.kotlin.Database.model.UserSearch
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.search.model.Search
import com.lightone.lighthouse.kotlin.src.search.model.SearchDataModel
import com.lightone.lighthouse.kotlin.src.suggest.model.SuggestDataModel
import com.lightone.lighthouse.kotlin.src.suggest.model.Suggests
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val dao: SearchDao,
    private val model: SearchDataModel,
    private val model2: SuggestDataModel
    ) : BaseViewModel() {

    private val TAG = "SearchViewModel"

    val _search = MutableLiveData<String>()
    val searchWord: LiveData<String>
        get() = _search

    private val _insertsuccessResponse = MutableLiveData<Boolean>()
    val insertsuccessResponse: LiveData<Boolean>
        get() = _insertsuccessResponse

    fun insertSearch(userSearch: UserSearch) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(userSearch).let {
                _insertsuccessResponse.postValue(true)
            }
        }
    }

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

    private val _tagResponse = MutableLiveData<List<Suggests>>()
    val tagResponse: LiveData<List<Suggests>>
        get() = _tagResponse

    fun tagList() {
        addDisposable(model2.getData(null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    _tagResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}