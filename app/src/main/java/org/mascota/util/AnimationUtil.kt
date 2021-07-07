package org.mascota.util

import android.animation.ObjectAnimator
import android.widget.ProgressBar
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.ONE_SECOND
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.PROGRESS_SIZE_100
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.PROGRESS_SIZE_50

object AnimationUtil {
    fun setProgress50Animation(progressBar : ProgressBar) {
        ObjectAnimator.ofInt(progressBar, "progress", PROGRESS_SIZE_50)
            .setDuration(ONE_SECOND)
            .start()
    }

    fun setProgress100Animation(progressBar : ProgressBar) {
        ObjectAnimator.ofInt(progressBar, "progress", PROGRESS_SIZE_100)
            .setDuration(ONE_SECOND)
            .start()
    }
}