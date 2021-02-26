package com.vmcorp.foodisu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vmcorp.foodisu.R
import com.vmcorp.foodisu.databinding.ViewDogListItemBinding
import com.vmcorp.foodisu.model.Meal

class MealListAdapter(private val data: MutableList<Meal>) :
    RecyclerView.Adapter<MealListAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.dog = data[position]
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ViewDogListItemBinding>(
            inflater,
            R.layout.view_dog_list_item,
            parent,
            false
        )
        return MyViewHolder(view)
    }


    class MyViewHolder(val view: ViewDogListItemBinding) : RecyclerView.ViewHolder(view.root)
}