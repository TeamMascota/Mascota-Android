package org.mascota.ui.view.home

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {
    @JvmStatic
    @BindingAdapter("imgUrl")
    fun setRemoteCircleImage(image: ImageView, url: String?) {
        Glide.with(image.context).load(url).into(image)
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("TitleText")
    fun setEpisodeText(textView: TextView, number: Int?) {
        textView.text = number.toString() + "화 기록하기"
    }
}