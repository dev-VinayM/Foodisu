package com.vmcorp.foodisu.listener

import com.vmcorp.foodisu.model.DogBreed

interface DogsRepositoryListener {
    fun onSuccess(dogList : MutableList<DogBreed>)
}