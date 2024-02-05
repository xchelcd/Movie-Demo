package com.idaxmx.moviedemo.di

import com.idaxmx.moviedemo.BuildConfig
import com.idaxmx.moviedemo.data.network.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private fun getMovieUrl(): String = BuildConfig.MOVIE_ROOT_URL

    @Singleton
    @Provides
    fun getMovieImageUrl(): String = BuildConfig.MOVIE_IMAGE_ROOT_URL

    private fun getClient(): OkHttpClient {
        val clientBuilder = OkHttpClient().newBuilder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Authorization", "Bearer ${BuildConfig.TOKEN}")
            return@Interceptor chain.proceed(request.build())
        })
        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideMovieRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(getMovieUrl())
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    //@Singleton
    //@Provides
    //fun provideMovieImageRetrofit(): Retrofit {
    //    return Retrofit.Builder()
    //        .baseUrl(getMovieImageUrl())
    //        .addConverterFactory(GsonConverterFactory.create())
    //        .build()
    //}


    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    //@Singleton
    //@Provides
    //fun provideMovieImageApiClient(retrofit: Retrofit): MovieImageApi {
    //    return retrofit.create(MovieImageApi::class.java)
    //}


}