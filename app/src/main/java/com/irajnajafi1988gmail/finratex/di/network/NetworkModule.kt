package com.irajnajafi1988gmail.finratex.di.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.irajnajafi1988gmail.finratex.BuildConfig
import com.irajnajafi1988gmail.finratex.data.remote.api.ApiDateService
import com.irajnajafi1988gmail.finratex.data.remote.api.ApiFinRateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    // Provide OkHttpClient with custom timeouts
    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

    // Provide Gson instance for JSON parsing
    @Provides
    @Singleton
    fun providerGson(): Gson = GsonBuilder().create()

    // Provide Retrofit instance configured with base URL, OkHttpClient, and Gson converter
    @Provides
    @Singleton
    fun providerRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // Provide ApiDateService for date-related API calls
    @Provides
    @Singleton
    fun providerApiDateService(retrofit: Retrofit): ApiDateService =
        retrofit.create(ApiDateService::class.java)

    // Provide ApiFinRateService for financial rate API calls
    @Provides
    @Singleton
    fun providerApiFinRateService(retrofit: Retrofit): ApiFinRateService =
        retrofit.create(ApiFinRateService::class.java)
}
