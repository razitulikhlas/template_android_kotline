package com.proyekakhir.paging3.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

abstract class BaseApi {
    suspend fun <T> flowApiCall(call: suspend () -> T) : Flow<Resource<T>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                emit(Resource.Success(call.invoke()))
                emit(Resource.Loading(false))
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        emit(
                            Resource.Failure(
                                false,
                                t.code(),
                                t.response()?.errorBody()
                            )
                        )
                        emit(Resource.Loading(false))
                    }
                    else -> {
                        emit(Resource.Failure(true, null, null))
                        emit(Resource.Loading(false))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}