package com.fitrianti.inibolaapp.app

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.fitrianti.inibolaapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FootballApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin {
            androidLogger()
            androidContext(this@FootballApp)
            modules(appModule)
        }
    }


}