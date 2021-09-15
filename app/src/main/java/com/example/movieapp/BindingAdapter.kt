package com.example.movieapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load


/**
 * Uses the coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, resourceId: Int?) {
    resourceId?.let {
        imgView.load(resourceId)
    }
}