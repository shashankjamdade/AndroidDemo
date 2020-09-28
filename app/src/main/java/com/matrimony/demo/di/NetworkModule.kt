package com.matrimony.demo.di

import com.matrimony.demo.api.NetworkAPIService
import com.matrimony.demo.api.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkingModule {

    @Provides
    fun providesBaseUrl(): String {
        return NetworkUtil.NETWORK_BASE_URL
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(NetworkUtil.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(NetworkUtil.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.readTimeout(NetworkUtil.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(NetworkUtil.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideRestApiService(retrofit: Retrofit): NetworkAPIService {
        return retrofit.create(NetworkAPIService::class.java)
    }
}

//@Module
//open class NetworkModule {
//
//    @Singleton
//    @Provides
//    fun getApiInterface(): NetworkAPIService? {
//        return provideCoroutineRetroInfo().create(NetworkAPIService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun  provideCoroutineRetroInfo() : Retrofit {
//        return  Retrofit
//            .Builder()
//            .baseUrl(NetworkUtil.NETWORK_BASE_URL)
//            .client(provideOkHttpClient())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    }
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient() : OkHttpClient {
//
//        val httpClient = OkHttpClient().newBuilder()
//            .connectTimeout(NetworkUtil.REQUEST_TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(NetworkUtil.REQUEST_TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(NetworkUtil.REQUEST_TIMEOUT, TimeUnit.SECONDS)
//
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        httpClient.addInterceptor(interceptor)
//        httpClient.addInterceptor(MyInterceptor())
//        return  httpClient.build();
//
//    }
//
//
//    class MyInterceptor : Interceptor {
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val original = chain.request()
//            val requestBuilder: Request.Builder = original.newBuilder()
//                .addHeader("Accept", "application/json")
//                .addHeader("Content-Type", "application/json")
//
//            val request = requestBuilder.build()
//            return chain.proceed(request)
//        }
//    }
//}