package com.gowthamraj07.tasklite.android

import android.app.Application
import com.gowthamraj07.tasklite.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaskLiteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TaskLiteApplication)
            modules(sharedModule)
        }
    }
}