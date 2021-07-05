package org.mascota.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.mascota.R
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_ANGRY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_BORING
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_JOY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_LOVE
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_SAD
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_USUAL
import org.mascota.ui.view.calendar.CalendarFragment.Companion.EMPTY

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imgEmo")
    fun setDogEmo(image: ImageView, emo: Int?) {
        with(image) {
            when (emo) {
                DOG_SAD -> {
                    setImageResource(R.drawable.ic_dog_sad)
                    visibility = View.VISIBLE
                }
                DOG_ANGRY -> {
                    setImageResource(R.drawable.ic_dog_angry)
                    visibility = View.VISIBLE
                }
                DOG_BORING -> {
                    setImageResource(R.drawable.ic_dog_boring)
                    visibility = View.VISIBLE
                }
                DOG_JOY -> {
                    setImageResource(R.drawable.ic_dog_joy)
                    visibility = View.VISIBLE
                }
                DOG_LOVE -> {
                    setImageResource(R.drawable.ic_dog_love)
                    visibility = View.VISIBLE
                }
                DOG_USUAL -> {
                    setImageResource(R.drawable.ic_dog_usual)
                    visibility = View.VISIBLE
                }
                EMPTY -> {
                    setImageResource(R.drawable.ic_empty)
                    visibility = View.VISIBLE
                }
                else -> visibility = View.INVISIBLE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("isEmpty")
    fun setRecordSizeVisibility(textView: TextView, isEmpty: Boolean?) {
        with(textView) {
            visibility = when (isEmpty) {
                true -> View.GONE
                false -> View.VISIBLE
                else -> View.GONE
            }
        }
    }
}