package com.cmm.wdflowers.modules

import com.cmm.wdflowers.repositories.Repository
import com.cmm.wdflowers.repositories.RepositoryImpl
import com.cmm.wdflowers.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }
}

val viewModelsModules = module {
    viewModel { MainViewModel(get()) }
}