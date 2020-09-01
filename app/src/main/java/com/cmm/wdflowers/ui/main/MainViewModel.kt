package com.cmm.wdflowers.ui.main

import androidx.lifecycle.ViewModel
import com.cmm.wdflowers.repositories.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {
    fun getUsers(): String {
        return repository.getOrder()
    }
}