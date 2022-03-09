package com.aelsayed.kaizen.di

import com.aelsayed.kaizen.data.local.AppDatabase
import com.aelsayed.kaizen.data.local.LocalDataSource
import com.aelsayed.kaizen.data.local.LocalDataSourceImp
import com.aelsayed.kaizen.data.remote.ApiService
import com.aelsayed.kaizen.data.remote.RemoteDataSource
import com.aelsayed.kaizen.data.remote.RemoteDataSourceImp
import com.aelsayed.kaizen.data.repository.ISportRepositoryImpl
import com.aelsayed.kaizen.domain.repository.ISportRepository
import org.koin.dsl.module






val appModule = module {

    single { createLocalDataSource(get()) }
    single { createRemoteDataSource(get()) }
    single<ISportRepository> { ISportRepositoryImpl(get(), get()) }

}
fun createLocalDataSource(appDatabase: AppDatabase): LocalDataSource {
    return LocalDataSourceImp(appDatabase)
}

fun createRemoteDataSource(apiService: ApiService): RemoteDataSource {
    return RemoteDataSourceImp(apiService)
}
