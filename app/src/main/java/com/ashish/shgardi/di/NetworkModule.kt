package com.ashish.shgardi.di


import com.ashish.delivery.data.remote.Endpoints
import com.ashish.shgardi.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val NETWORK_CALL_TIMEOUT = 90L

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    @Named("Retrofit1")
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService( @Named("Retrofit1") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}