package org.mascota.util

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import org.mascota.R

object DialogUtil {
    fun makeDialog(context : Context) = Dialog(context, R.style.MascotaDialogStyle)

    fun setDialog(dialog : Dialog, view : View) {
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(view)

            with(window?.attributes) {
                this?.width = WindowManager.LayoutParams.MATCH_PARENT
                this?.height = WindowManager.LayoutParams.WRAP_CONTENT
                this?.verticalWeight = 1F
            }
        }
    }
}