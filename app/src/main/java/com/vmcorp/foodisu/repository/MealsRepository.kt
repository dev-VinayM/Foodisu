package com.vmcorp.foodisu.repository

import android.app.Application
import com.vmcorp.foodisu.listener.MealsRepositoryListener
import com.vmcorp.foodisu.localDataStorage.MealDao
import com.vmcorp.foodisu.localDataStorage.MealDatabase
import com.vmcorp.foodisu.model.MealList
import com.vmcorp.foodisu.remoteDataHelper.MealApi
import com.vmcorp.foodisu.remoteDataHelper.MealApiService
import com.vmcorp.foodisu.util.safeApiCall
import retrofit2.Retrofit

class MealsRepository(
    private val mealsRepositoryListener: MealsRepositoryListener,
    application: Application
) : BaseRepository() {

    private val dao: MealDao = MealDatabase(application).mealDao()
    private val retrofit: Retrofit = MealApiService.getInstance()!!
    private var mealApi: MealApi = retrofit.create(MealApi::class.java)

    suspend fun getMealList() {
        val apiResponse = safeApiCall(
            call = { mealApi.getMealListAsync().await() },
            errorMessage = "Error Fetching Meal List"
        )
        apiResponse?.toMutableList()?.let { dogsRepositoryListener.onSuccess(it) }
    }

    suspend fun getMealFromKeyId(keyId: Int) {
        mealsRepositoryListener.onMealDetailsFetched(dao.getAllMeals(keyId))
    }

    private suspend fun storeMealsLocally(mealList: MealList) {
        dao.deleteAllMeals()
        val result = dao.insertAllMeals(*mealList.meal.toTypedArray())
        var i = 0
        while (i < mealList.meal.size) {
            mealList.meal[i].keyId = result[i].toInt()
            ++i
        }
        mealsRepositoryListener.onSuccess(mealList.meal.toMutableList())
    }
}