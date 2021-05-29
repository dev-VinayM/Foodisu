package com.vmcorp.foodisu.view

import android.os.Bundle
import com.vmcorp.foodisu.depenpencyInjection.mealsViewModel.DaggerMealsViewModelComponent
import com.vmcorp.foodisu.depenpencyInjection.mealsViewModel.MealsViewModelComponent
import com.vmcorp.foodisu.depenpencyInjection.mealsViewModel.MealsViewModelModule
import com.vmcorp.foodisu.viewmodel.MealsViewModel
import javax.inject.Inject

/**
 * Landing screen activity - holds fragments
 */

class HomeActivity : BaseActivity() {

    lateinit var mealsViewModelComponent: MealsViewModelComponent

    @Inject
    lateinit var viewmodel: MealsViewModel      //field injecting viewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vmcorp.foodisu.R.layout.activity_home)
        mealsViewModelComponent = initDagger()      //init Dagger component
        mealsViewModelComponent.inject(this)    //this will inject instance of viewmodel (specific to this activity)

    }

    /***
     * method to initialize Dagger Component
     */
    private fun initDagger(): MealsViewModelComponent =
        DaggerMealsViewModelComponent.builder()
            .mealsViewModelModule(
                MealsViewModelModule(
                    this
                )
            )
            .build()
}
