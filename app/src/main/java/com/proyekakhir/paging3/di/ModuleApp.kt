package com.proyekakhir.paging3.di

import com.proyekakhir.paging3.adapter.MoviesAdapter
import com.proyekakhir.paging3.data.network.RemoteDataSource
import com.proyekakhir.paging3.data.network.Service
import com.proyekakhir.paging3.repository.RepositoryMovies
import com.proyekakhir.paging3.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Singleton
    @Provides
    fun retrofitInstance(): Service {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

       val interceptor = OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

       return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptor)
            .build().create(Service::class.java)
    }

    @Provides
    fun adapter() = MoviesAdapter()

    @Provides
    fun remoteDataSource(service: Service): RemoteDataSource = RemoteDataSource(service)

    @Provides
    fun repositoryMovies(remoteDataSource: RemoteDataSource): RepositoryMovies =
        RepositoryMovies(remoteDataSource)
}