package com.cmm.wdflowers.ui.base

import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmm.wdflowers.R

open class BaseViewModel : ViewModel() {

    // Observable for data content
    val contentVisibility = MutableLiveData<Int>().apply { View.GONE }

    // Observables for empty state
    val emptyStateVisibility = MutableLiveData<Int>().apply { View.GONE }
    val emptyStateMessage = MutableLiveData(R.string.orders_empty_state)

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
        R.string.orders_empty_state
    }

    protected fun setErrorState(@StringRes stringResId: Int) {
        contentVisibility.postValue(View.GONE)
        loadingStateVisibility.postValue(View.GONE)
        emptyStateVisibility.postValue(View.VISIBLE)
        emptyStateMessage.postValue(stringResId)
    }
}