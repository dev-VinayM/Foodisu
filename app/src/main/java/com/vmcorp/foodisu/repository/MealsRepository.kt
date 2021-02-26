package com.vmcorp.foodisu.repository

import com.vmcorp.foodisu.listener.MealsRepositoryListener
import com.vmcorp.foodisu.remoteDataHelper.MealApi
import com.vmcorp.foodisu.remoteDataHelper.MealApiService
import com.vmcorp.foodisu.util.safeApiCall
import retrofit2.Retrofit

class MealsRepository(
        private val mealsRepositoryListener: MealsRepositoryListener
) : BaseRepository() {
    private val retrofit: Retrofit = MealApiService.getInstance()!!
    private var mealApi: MealApi = retrofit.create(MealApi::class.java)

    suspend fun getDogsList() {
        val apiResponse = safeApiCall(
                call = { mealApi.getDogLists().await() },
                errorMessage = "Error Fetching Popular Movies"
        )
        apiResponse?.toMutableList()?.let { dogsRepositoryListener.onSuccess(it) }
    }
}