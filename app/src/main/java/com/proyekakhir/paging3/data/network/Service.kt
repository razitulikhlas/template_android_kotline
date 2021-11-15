package com.proyekakhir.paging3.data.network

import com.proyekakhir.paging3.data.network.response.ResponseMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("movie/popular?api_key=15bd1c26c3ee8ac797dc6f96348db0a6")
    suspend fun getMoviesPopular(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): ResponseMovies
}