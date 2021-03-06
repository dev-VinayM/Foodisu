package com.vmcorp.foodisu.depenpencyInjection.mealsRepository

import com.vmcorp.foodisu.viewmodel.MealsViewModel
import dagger.Component

@Component (modules = [MealsRepositoryModule::class])
interface MealsRepositoryComponent {

    fun inject(mealsViewModel: MealsViewModel)
}