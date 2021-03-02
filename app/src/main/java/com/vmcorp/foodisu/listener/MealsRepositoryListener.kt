package com.vmcorp.foodisu.listener

import com.vmcorp.foodisu.model.Meal

interface MealsRepositoryListener {
    fun onSuccess(mutableList: MutableList<Meal>)

    fun onMealDetailsFetched(meal: Meal)
}