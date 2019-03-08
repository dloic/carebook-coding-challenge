package com.jrd.carebook.dagger2

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jrd.carebook.activities.Router
import com.jrd.carebook.data.Repository
import com.jrd.carebook.model.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.disableHtmlEscaping()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = Constants.CACHE_SIZE
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        /**
         * Necessary to prevent encoding of the url and make sure the next bundle is fetched correctly
         */
        client.interceptors().add(Interceptor { chain ->
            var string = chain.request().url().toString()
            string = string.replace("%3F", "?")
            val newRequest = Request.Builder().url(string).build()
            chain.proceed(newRequest)
        })
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.API_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(application: Application): Repository {
        return Repository(application as MyApplication)
    }

    @Provides
    @Singleton
    fun provideRouter(application: Application): Router {
        return Router(application.applicationContext)
    }
}