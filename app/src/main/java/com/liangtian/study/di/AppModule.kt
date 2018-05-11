package com.liangtian.study.di

import com.liangtian.study.api.BookService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by zhuliangtian on 2018/5/10.
 */
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideName(): String {
        return "zlt"
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        return okHttpClient;
    }

    @Singleton
    @Provides
    fun provideBookApi(okHttpClient: OkHttpClient): BookService {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://api.zhuishushenqi.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(BookService::class.java)
    }
}