package com.vmcorp.foodisu.repository

import com.vmcorp.foodisu.listener.DogsRepositoryListener
import com.vmcorp.foodisu.remoteDataHelper.DogsApi
import com.vmcorp.foodisu.remoteDataHelper.DogsApiService
import com.vmcorp.foodisu.util.safeApiCall
import retrofit2.Retrofit

class DogsRepository(
    private val dogsRepositoryListener: DogsRepositoryListener
) : BaseRepository() {
    private val retrofit: Retrofit = DogsApiService.getInstance()!!
    private var dogsApi: DogsApi = retrofit.create(DogsApi::class.java)

    suspend fun getDogsList() {
        val apiResponse = safeApiCall(
            call = { dogsApi.getDogLists().await() },
            errorMessage = "Error Fetching Popular Movies"
        )
        apiResponse?.toMutableList()?.let { dogsRepositoryListener.onSuccess(it) }
    }
}