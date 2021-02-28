package com.vmcorp.foodisu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.vmcorp.foodisu.R
import com.vmcorp.foodisu.databinding.ViewMealListItemBinding
import com.vmcorp.foodisu.listener.MealClickListener
import com.vmcorp.foodisu.model.Meal
import com.vmcorp.foodisu.view.DetailsFragment
import com.vmcorp.foodisu.view.ListsFragment
import com.vmcorp.foodisu.view.ListsFragmentDirections
import kotlinx.android.synthetic.main.view_meal_list_item.view.*

class MealListAdapter(private val data: MutableList<Meal>) :
    RecyclerView.Adapter<MealListAdapter.MyViewHolder>(), MealClickListener {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.dog = data[position]
        holder.view.listener = this
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ViewMealListItemBinding>(
            inflater,
            R.layout.view_meal_list_item,
            parent,
            false
        )
        return MyViewHolder(view)
    }


    class MyViewHolder(val view: ViewMealListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onMealClicked(view: View) {
        val mealId = view.tv_mealId.text.toString()
        val action = ListsFragmentDirections.actionDetailsFragment(mealId)
        Navigation.findNavController(view).navigate(action)
    }
}