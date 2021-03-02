package com.vmcorp.foodisu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vmcorp.foodisu.listener.MealsRepositoryListener
import com.vmcorp.foodisu.model.Meal
import com.vmcorp.foodisu.repository.MealsRepository
import com.vmcorp.foodisu.util.NotificationHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MealsViewModel(application: Application) : AndroidViewModel(application),
    MealsRepositoryListener {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository = MealsRepository(this, application)

    val mealListData = MutableLiveData<MutableList<Meal>>()
    val mealDetailsLiveData = MutableLiveData<Meal>()

    init {
        scope.launch {
            repository.getDogsList()
        }
    }

    override fun onSuccess(mutableList: MutableList<Meal>) {
        NotificationHelper(getApplication()).createNotification()
        mealListData.postValue(mutableList)
    }

    fun getMealFromKeyId(keyId: Int) {
        scope.launch {
            repository.getMealFromKeyId(keyId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    override fun onMealDetailsFetched(meal: Meal) {
        mealDetailsLiveData.postValue(meal)
    }
}