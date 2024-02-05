package com.idaxmx.moviedemo.di

import com.idaxmx.moviedemo.data.repository.LoginCredentialsRepositoryImp
import com.idaxmx.moviedemo.data.repository.LoginRepository
import com.idaxmx.moviedemo.data.repository.MovieRepository
import com.idaxmx.moviedemo.data.repository.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Singleton
    @Binds
    abstract fun providerHomeRepository(
        repositoryImpl: MovieRepositoryImp
    ): MovieRepository

    @Singleton
    @Binds
    abstract fun providerLoginCredentialsRepository(
        repositoryImpl: LoginCredentialsRepositoryImp
    ): LoginRepository

    //@Singleton
    //@Binds
    //abstract fun providerLoginFacebookRepository(
    //    repositoryImpl: LoginFacebookRepositoryImp
    //): LoginRepository

}