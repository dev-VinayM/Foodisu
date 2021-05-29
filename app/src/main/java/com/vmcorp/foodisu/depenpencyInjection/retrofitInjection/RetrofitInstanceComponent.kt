package com.vmcorp.foodisu.depenpencyInjection.retrofitInjection

import com.vmcorp.foodisu.repository.MealsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [RetrofitInstanceModule::class])
interface RetrofitInstanceComponent {
    fun inject(mealsRepository : MealsRepository)
}