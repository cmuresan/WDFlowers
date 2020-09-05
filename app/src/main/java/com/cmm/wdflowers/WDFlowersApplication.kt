package com.cmm.wdflowers

import android.app.Application
import com.cmm.wdflowers.di.modules.appModule
import com.cmm.wdflowers.di.modules.repositoryModule
import com.cmm.wdflowers.di.modules.viewModelsModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WDFlowersApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin.
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@WDFlowersApplication)
            modules(listOf(appModule, repositoryModule, viewModelsModules))
        }
    }
}