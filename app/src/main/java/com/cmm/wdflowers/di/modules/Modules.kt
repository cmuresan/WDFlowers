package com.cmm.wdflowers.di.modules

import com.cmm.wdflowers.BuildConfig
import com.cmm.wdflowers.datasource.OrdersDataSource
import com.cmm.wdflowers.datasource.OrdersDataSourceImpl
import com.cmm.wdflowers.datasource.api.OrdersApi
import com.cmm.wdflowers.repositories.Repository
import com.cmm.wdflowers.repositories.RepositoryImpl
import com.cmm.wdflowers.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single<OrdersApi> { provideOrdersApi(get()) }
    single<OrdersDataSource> { OrdersDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get()) }
}

val viewModelsModules = module {
    viewModel { MainViewModel(get()) }
}

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .readTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(10, TimeUnit.SECONDS)
    .connectTimeout(10, TimeUnit.SECONDS)
    .addInterceptor(getLoggingInterceptor())
    .build()

private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}

private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private fun provideOrdersApi(retrofit: Retrofit) = retrofit.create(OrdersApi::class.java)
