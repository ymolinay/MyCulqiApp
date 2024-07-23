package com.heyrex.myculqiapp.core.utils

import retrofit2.Response

fun <T, R> Response<T>.safeCall(
    transform: (T) -> R,
    errorFactory: Throwable = Throwable()
): Result<R> {
    val body = body()
    return when {
        isSuccessful && body != null -> Result.success(transform(body))
        else -> Result.failure(errorFactory)
    }
}