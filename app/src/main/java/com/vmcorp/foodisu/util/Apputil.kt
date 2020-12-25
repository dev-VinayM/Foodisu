package com.vmcorp.foodisu.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions


fun getProgressDrawable(context: Context): CircularProgressDrawable{
return CircularProgressDrawable(context).apply {
    strokeWidth = 10f
    centerRadius = 50f
    start()
}
}

fun ImageView.loadImage( url : String? , progressDrawable: CircularProgressDrawable){

    // new since Glide v4
    @GlideModule
    class MyAppGlideModule : AppGlideModule()
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(com.vmcorp.foodisu.R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView,url: String?){
    view.loadImage(url, getProgressDrawable(view.context))
}
