package com.cmm.wdflowers.ui.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    // Observable for data content
    val contentVisibility = MutableLiveData<Int>().apply { View.GONE }

    // Observables for empty state
    val emptyStateVisibility = MutableLiveData<Int>().apply { View.GONE }

    // Observables for loading view
    val loadingStateVisibility = MutableLiveData<Int>().apply { View.GONE }

    protected fun setLoadingState() {
        contentVisibility.postValue(View.GONE)
        loadingStateVisibility.postValue(View.VISIBLE)
        emptyStateVisibility.postValue(View.GONE)
    }

    protected fun setContentState() {
        contentVisibility.postValue(View.VISIBLE)
        loadingStateVisibility.postValue(View.GONE)
        emptyStateVisibility.postValue(View.GONE)
    }

    protected fun setEmptyState() {
        contentVisibility.postValue(View.GONE)
        loadingStateVisibility.postValue(View.GONE)
        emptyStateVisibility.postValue(View.VISIBLE)
    }
}