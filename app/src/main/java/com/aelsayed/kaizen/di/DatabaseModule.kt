package com.aelsayed.kaizen.di

import android.app.Application
import androidx.room.Room
import com.aelsayed.kaizen.data.local.AppDatabase
import org.koin.dsl.module


val databaseModule = module {

    single { createAppDatabase(get()) }

}

internal fun createAppDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "kaizen_db"
    ).build()
}