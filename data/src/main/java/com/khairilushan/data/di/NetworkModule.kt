package com.khairilushan.data.di

import com.khairilushan.data.network.RestApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by khairil on 11/16/17.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providerRestApi(@Named("is_debug") isDebug: Boolean): RestApi {
        return RestApi(getRetrofit(isDebug))
    }

    private fun getRetrofit(isDebug: Boolean): Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(getOkHttpClient(isDebug))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private fun getOkHttpClient(isDebug: Boolean): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (isDebug) {
            val loggingInterceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

}