package org.mascota.ui.view.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {
    @JvmStatic
    @BindingAdapter("imgResId")
    fun setRemoteCircleImage(image: ImageView, url: String?) {
        Glide.with(image.context).load(url).into(image)
    }
}