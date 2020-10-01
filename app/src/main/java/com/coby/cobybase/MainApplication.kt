package com.coby.cobybase

import android.app.Application
import com.coby.cobybase.di.appModule
import com.coby.cobybase.di.localModule
import com.coby.cobybase.di.remoteModule
import com.coby.cobybase.di.repositoryModule
import com.coby.cobybase.model.User
import com.coby.cobybase.utils.PrefUtil
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    private val prefUtil: PrefUtil by inject()

    override fun onCreate() {
        super.onCreate()
        instance = this

        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(localModule, remoteModule, repositoryModule, appModule))
        }
    }

    fun isNetworkConnected(): Boolean {
        return prefUtil.isNetworkConnected()
    }

    fun saveUser(user: User?) {
        prefUtil.user = user
    }

    fun getUser(): User? = prefUtil.user

    fun clearPref() = prefUtil.clearAllData()

}