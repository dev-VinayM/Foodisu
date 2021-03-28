package com.vmcorp.foodisu.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vmcorp.foodisu.R
import com.vmcorp.foodisu.databinding.FragmentDetailsBinding
import com.vmcorp.foodisu.viewmodel.MealsViewModel

class DetailsFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailsBinding
    private lateinit var viewModel: MealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MealsViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getMealFromKeyId(DetailsFragmentArgs.fromBundle(it).keyId)
        }

        viewModel.mealDetailsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                dataBinding.meal = it
            }
        })

    }

}
