package com.vmcorp.foodisu.remoteDataHelper

import com.vmcorp.foodisu.model.DogBreed
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface DogsApi {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogLists() : Deferred<Response<List<DogBreed>>>

}