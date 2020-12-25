package com.vmcorp.foodisu.state

sealed class NetworkReponseState<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkReponseState<T>()
    data class Error(val exception: Exception) : NetworkReponseState<Nothing>()
}