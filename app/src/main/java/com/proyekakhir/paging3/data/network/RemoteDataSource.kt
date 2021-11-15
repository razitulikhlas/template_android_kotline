package com.proyekakhir.paging3.data.network

class RemoteDataSource(private val service: Service) :BaseApi() {

    suspend fun getMoviesPopular(page:Int)=flowApiCall {
        service.getMoviesPopular(page = page)
    }
}