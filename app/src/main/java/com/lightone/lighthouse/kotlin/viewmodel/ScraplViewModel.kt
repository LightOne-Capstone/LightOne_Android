package com.lightone.lighthouse.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lightone.lighthouse.kotlin.Database.dao.UserScrapDao
import com.lightone.lighthouse.kotlin.Database.model.UserScrap
import com.lightone.lighthouse.kotlin.config.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ScraplViewModel(private val dao: UserScrapDao) : BaseViewModel() {

    private val TAG = "ScraplViewModel"

    fun getScrapList(): LiveData<List<UserScrap>> = dao.getAll()

    private val _deletescrapResponse = MutableLiveData<Boolean>()
    val deleteResponse: LiveData<Boolean>
        get() = _deletescrapResponse

    fun deleteScrap(idx: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteScrap(idx).let {
                _deletescrapResponse.postValue(true)
            }
        }
    }
}