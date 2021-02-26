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

    private val repository = MealsRepository(this)

    val dogListData = MutableLiveData<MutableList<Meal>>()

    init {
        scope.launch {
            repository.getDogsList()
        }
    }

    override fun onSuccess(dogList: MutableList<Meal>) {
        NotificationHelper(getApplication()).createNotification()
        dogListData.postValue(dogList)
    }

    fun cancelAllRequests() = coroutineContext.cancel()

}