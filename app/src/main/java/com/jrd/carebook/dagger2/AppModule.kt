package com.jrd.carebook.dagger2

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var application: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return application
    }
}