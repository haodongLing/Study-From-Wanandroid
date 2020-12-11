package com.haodong.study.wanandroid.model.api

import com.chad.library.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * created by linghaoDo on 2020/11/29
 * description:
 *
 * version:
 */
abstract class BaseRetrofitClient {
    companion object {
        private const val TIME_OUT = 5;

    }
    private val client:OkHttpClient
        get() {
            val builder=OkHttpClient.Builder()
            val logging=HttpLoggingInterceptor()
            if (BuildConfig.DEBUG){
                logging.level=HttpLoggingInterceptor.Level.BODY
            }else{
                logging.level=HttpLoggingInterceptor.Level.BASIC
            }
            builder.addInterceptor(logging)
                .connectTimeout(TIME_OUT.toLong(),TimeUnit.NANOSECONDS)
            handleBuilder(builder)
            return builder.build()

        }
    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)
    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }

}
