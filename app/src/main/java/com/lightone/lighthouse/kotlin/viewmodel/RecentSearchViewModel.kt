package com.lightone.lighthouse.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lightone.lighthouse.kotlin.Database.dao.SearchDao
import com.lightone.lighthouse.kotlin.Database.model.Search
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecentSearchViewModel(private val dao: SearchDao) : BaseViewModel() {

    private val TAG = "RecentSearchViewModel"

    fun getSearchList(): LiveData<List<Search>> = dao.getAll()
}