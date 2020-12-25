package com.vmcorp.foodisu.remoteDataHelper

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogsApiService {

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"
        private var instance: Retrofit? = null


        @Synchronized
        fun getInstance(): Retrofit? {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            }
            return instance
        }
    }
}