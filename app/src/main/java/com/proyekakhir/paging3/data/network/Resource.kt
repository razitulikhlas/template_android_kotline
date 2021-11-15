package com.proyekakhir.paging3.data.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Loading(val loading: Boolean) : Resource<Nothing>()
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

}