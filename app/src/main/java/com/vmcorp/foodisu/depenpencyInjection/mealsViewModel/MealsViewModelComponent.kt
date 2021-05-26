package com.vmcorp.foodisu.depenpencyInjection.mealsViewModel

import com.vmcorp.foodisu.view.HomeActivity
import dagger.Component

@Component(modules = [MealsViewModelModule::class])
interface MealsViewModelComponent {
    fun inject(homeActivity: HomeActivity)
}