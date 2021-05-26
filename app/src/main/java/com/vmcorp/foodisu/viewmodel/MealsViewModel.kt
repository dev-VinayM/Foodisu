package com.vmcorp.foodisu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vmcorp.foodisu.depenpencyInjection.mealsRepository.DaggerMealsRepositoryComponent
import com.vmcorp.foodisu.depenpencyInjection.mealsRepository.MealsRepositoryComponent
import com.vmcorp.foodisu.depenpencyInjection.mealsRepository.MealsRepositoryModule
import com.vmcorp.foodisu.listener.MealsRepositoryListener
import com.vmcorp.foodisu.model.Meal
import com.vmcorp.foodisu.repository.MealsRepository
import com.vmcorp.foodisu.util.NotificationHelper
import kotlinx.coroutines.launch
import javax.inject.Inject

class MealsViewModel(application: Application) : AndroidViewModel(application),
    MealsRepositoryListener {

    var mealsRepositoryComponent: MealsRepositoryComponent

    @Inject
    lateinit var repository: MealsRepository

    val mealListData = MutableLiveData<MutableList<Meal>>()
    val mealDetailsLiveData = MutableLiveData<Meal>()

    init {
        mealsRepositoryComponent = initDagger(application)
        mealsRepositoryComponent.inject(this)

        viewModelScope.launch {
            repository.getMealList()
        }
    }

    override fun onSuccess(mutableList: MutableList<Meal>) {
        NotificationHelper(getApplication()).createNotification()
        mealListData.postValue(mutableList)
    }

    fun getMealFromKeyId(keyId: Int) {
        viewModelScope.launch {
            repository.getMealFromKeyId(keyId)
        }
    }

    override fun onMealDetailsFetched(meal: Meal) {
        mealDetailsLiveData.postValue(meal)
    }

    private fun initDagger(application: Application): MealsRepositoryComponent =
        DaggerMealsRepositoryComponent.builder()
            .mealsRepositoryModule(
                MealsRepositoryModule(
                    application,
                    this
                )
            )
            .build()
}