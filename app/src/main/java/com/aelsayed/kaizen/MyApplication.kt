package com.aelsayed.kaizen

import android.app.Application
import com.aelsayed.kaizen.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    databaseModule,
                    useCasesModule,
                    viewModelsModule
                )
            )
        }
    }
}