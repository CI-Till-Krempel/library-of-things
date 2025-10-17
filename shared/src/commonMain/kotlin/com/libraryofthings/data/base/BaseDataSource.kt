package com.libraryofthings.data.base

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

public abstract class BaseDataSource(public val dispatcher: CoroutineDispatcher) {

    public inline suspend fun <reified T> safeApiCall(crossinline apiCall: suspend () -> HttpResponse): Result<T> = withContext(dispatcher) {
        try {
            val response = apiCall()
            if (response.status.isSuccess()) {
                Result.success(response.body())
            } else {
                // You can create a custom exception class for API errors
                Result.failure(Exception("API call failed with status: ${response.status}"))
            }
        } catch (e: Exception) {
            // Log the exception
            Result.failure(e)
        }
    }
}
