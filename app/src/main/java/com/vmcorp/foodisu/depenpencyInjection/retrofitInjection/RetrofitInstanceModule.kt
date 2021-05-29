package com.vmcorp.foodisu.depenpencyInjection.retrofitInjection

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vmcorp.foodisu.remoteDataHelper.MealApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitInstanceModule {
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    @Singleton
    @Provides
    fun providesRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}