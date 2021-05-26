package com.vmcorp.foodisu.depenpencyInjection

import android.app.Application
import com.vmcorp.foodisu.listener.MealsRepositoryListener
import com.vmcorp.foodisu.repository.MealsRepository
import dagger.Module
import dagger.Provides

@Module
class MealsRepositoryModule(
    private val application: Application,
    private val mealsRepositoryListener: MealsRepositoryListener
) {

    @Provides
    fun providesMealsRepository(): MealsRepository {
        return MealsRepository(
            mealsRepositoryListener,
            application
        )
    }
}