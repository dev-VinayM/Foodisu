package com.vmcorp.foodisu.depenpencyInjection.mealsViewModel

import androidx.lifecycle.ViewModelProviders
import com.vmcorp.foodisu.view.HomeActivity
import com.vmcorp.foodisu.viewmodel.MealsViewModel
import dagger.Module
import dagger.Provides

@Module
class MealsViewModelModule(
    private val homeActivity: HomeActivity
) {
    @Provides
    fun providesMealsViewModel(): MealsViewModel {
        return ViewModelProviders.of(homeActivity).get(MealsViewModel::class.java)
    }
}