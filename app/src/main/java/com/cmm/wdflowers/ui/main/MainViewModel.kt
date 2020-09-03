package com.cmm.wdflowers.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmm.wdflowers.datasource.model.Flower
import com.cmm.wdflowers.datasource.model.Status
import com.cmm.wdflowers.repositories.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    //TODO should I make a BaseViewModel for state handling? (loading/empty/error/content) + data binding

    private val orderLiveData = MutableLiveData<List<Flower>>()

    fun order(): LiveData<List<Flower>> = orderLiveData

    fun getOrder() {
        //todo handle loading
        viewModelScope.launch {
            repository.getOrder().let { resource ->
                when (resource.status) {
                    Status.Success -> {
                        orderLiveData.postValue(resource.payload)
                    }
                    Status.Error -> {
                        //todo handle error
                    }
                }
            }
        }
    }
}
