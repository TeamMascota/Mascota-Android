package org.mascota.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.mascota.R
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_ANGRY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_BORING
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_JOY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_LOVE
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_SAD

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imgEmo")
    fun setDogEmo(image: ImageView, emo: Int?) {
        with(image) {
            when (emo) {
                DOG_SAD -> setImageResource(R.drawable.ic_dog_sad)
                DOG_ANGRY -> setImageResource(R.drawable.ic_dog_angry)
                DOG_BORING -> setImageResource(R.drawable.ic_dog_boring)
                DOG_JOY -> setImageResource(R.drawable.ic_dog_joy)
                DOG_LOVE -> setImageResource(R.drawable.ic_dog_love)
                else -> setImageResource(R.drawable.ic_dog_usual)
            }
        }
    }
}