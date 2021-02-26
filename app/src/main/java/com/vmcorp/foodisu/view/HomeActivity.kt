package com.vmcorp.foodisu.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.vmcorp.foodisu.viewmodel.MealsViewModel


class HomeActivity : BaseActivity() {
    private lateinit var viewmodel: MealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vmcorp.foodisu.R.layout.activity_home)
        viewmodel = ViewModelProviders.of(this).get(MealsViewModel::class.java)
    }
}
