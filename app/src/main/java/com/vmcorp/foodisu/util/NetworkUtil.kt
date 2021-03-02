package com.vmcorp.foodisu.util

import android.util.Log
import com.vmcorp.foodisu.state.NetworkReponseState
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
    val result: NetworkReponseState<T> = safeApiResult(call, errorMessage)
    var data: T? = null

    when (result) {
        is NetworkReponseState.Success ->
            data = result.data
        is NetworkReponseState.Error -> {
            Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
        }
    }
    return data
}


private suspend fun <T : Any> safeApiResult(
    call: suspend () -> Response<T>,
    errorMessage: String
): NetworkReponseState<T> {
    val response = call.invoke()
    if (response.isSuccessful) return NetworkReponseState.Success(response.body()!!)

    return NetworkReponseState.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
}