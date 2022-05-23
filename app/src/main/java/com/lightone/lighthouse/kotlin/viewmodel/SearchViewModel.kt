package com.lightone.lighthouse.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lightone.lighthouse.kotlin.Database.dao.SearchDao
import com.lightone.lighthouse.kotlin.Database.model.UserSearch
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import com.lightone.lighthouse.kotlin.src.search.model.SearchDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val dao: SearchDao) : BaseViewModel() {

    private val TAG = "SearchViewModel"

    private val _insertsuccessResponse = MutableLiveData<Boolean>()
    val insertsuccessResponse: LiveData<Boolean>
        get() = _insertsuccessResponse

    fun search(userSearch: UserSearch) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(userSearch).let {
                _insertsuccessResponse.postValue(true)
            }
        }
    }
}