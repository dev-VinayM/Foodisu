package com.vmcorp.foodisu.localDataStorage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vmcorp.foodisu.model.Meal

@Dao
interface MealDao {
    @Insert
    suspend fun insertAllMeals(vararg meals : Meal) : List<Long>         //vararg => multiple argument of type meal AND return list will contain list of primary keys

    @Query("SELECT * FROM meal")
    suspend fun getAllMeals() : List<Meal>

    @Query("SELECT * FROM meal WHERE keyId = :keyId")
    suspend fun getAllMeals(keyId : Int) : Meal

    @Query("DELETE FROM meal")
    suspend fun deleteAllMeals()
}