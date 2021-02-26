package com.vmcorp.foodisu.model

import com.google.gson.annotations.SerializedName

data class MealList(
    @SerializedName(value = "meals")
    val meal: List<Meal>
)