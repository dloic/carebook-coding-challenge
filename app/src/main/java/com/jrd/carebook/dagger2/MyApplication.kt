package com.jrd.carebook.dagger2

import android.app.Application

class MyApplication: Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .build()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }
}