package org.mascota.util.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.Px
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import org.mascota.R
import kotlin.math.roundToInt

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(frameId, fragment, null)
        .commit()
}

fun Context.urlIntent(url : String) {
    with(Intent(Intent.ACTION_VIEW)) {
        data = Uri.parse(url)
        startActivity(this)
    }
}

fun View.setTextPartialColor(@ColorRes res: Int, start : Int, end : Int, text : String): SpannableString {
    val resultString = SpannableString(text)
    resultString.setSpan(ForegroundColorSpan(getColor(res)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    return resultString
}

fun Fragment.setTextPartialColor(@ColorRes res: Int, start : Int, end : Int, text : String): SpannableString {
    val resultString = SpannableString(text)
    resultString.setSpan(ForegroundColorSpan(getColor(res)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    return resultString
}

@Px
fun View.px(dp: Int) = (dp * resources.displayMetrics.density).roundToInt()