package com.vmcorp.foodisu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vmcorp.foodisu.listener.DogsRepositoryListener
import com.vmcorp.foodisu.model.DogBreed
import com.vmcorp.foodisu.repository.DogsRepository
import com.vmcorp.foodisu.util.NotificationHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DogsViewModel(application: Application) : AndroidViewModel(application),
    DogsRepositoryListener {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository = DogsRepository(this)

    val dogListData = MutableLiveData<MutableList<DogBreed>>()

    init {
        scope.launch {
            repository.getDogsList()
        }
    }

    override fun onSuccess(dogList: MutableList<DogBreed>) {
        NotificationHelper(getApplication()).createNotification()
        dogListData.postValue(dogList)
    }

    fun cancelAllRequests() = coroutineContext.cancel()

}