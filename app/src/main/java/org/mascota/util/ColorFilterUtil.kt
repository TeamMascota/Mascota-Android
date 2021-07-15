package org.mascota.util

import android.widget.ImageView
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import org.mascota.R

object ColorFilterUtil {
    fun setImgFilter(imageView : ImageView){
        imageView.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(getColor(imageView.context,
            R.color.maco_black_alpha_30), BlendModeCompat.SRC_OVER)
    }
}