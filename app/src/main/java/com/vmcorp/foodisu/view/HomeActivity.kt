package com.vmcorp.foodisu.view

import android.os.Bundle
import com.vmcorp.foodisu.depenpencyInjection.mealsViewModel.DaggerMealsViewModelComponent
import com.vmcorp.foodisu.depenpencyInjection.mealsViewModel.MealsViewModelComponent
import com.vmcorp.foodisu.depenpencyInjection.mealsViewModel.MealsViewModelModule
import com.vmcorp.foodisu.viewmodel.MealsViewModel
import javax.inject.Inject


class HomeActivity : BaseActivity() {
    @Inject
    lateinit var viewmodel: MealsViewModel

    lateinit var mealsViewModelComponent: MealsViewModelComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vmcorp.foodisu.R.layout.activity_home)
//        viewmodel = ViewModelProviders.of(this).get(MealsViewModel::class.java)
        mealsViewModelComponent = initDagger()
        mealsViewModelComponent.inject(this)

    }

    private fun initDagger(): MealsViewModelComponent =
        DaggerMealsViewModelComponent.builder()
            .mealsViewModelModule(
                MealsViewModelModule(
                    this
                )
            )
            .build()
}
