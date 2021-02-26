package com.vmcorp.foodisu.remoteDataHelper

import com.vmcorp.foodisu.model.MealList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MealApi {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogLists() : Deferred<Response<List<DogBreed>>>

}