package org.mascota.ui.view.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import org.mascota.R
import org.mascota.databinding.ItemDateBinding
import org.mascota.util.CalendarUtil.isDaySame
import org.mascota.util.CalendarUtil.isMonthSame
import org.mascota.util.extension.getColor
import java.util.*

class MonthView(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private val nowCalendar = Calendar.getInstance(Locale.KOREA)

    private val dateItems = (1..42).map { _ ->
        ItemDateBinding.inflate(LayoutInflater.from(context), null, false).apply {
            root.id = ViewCompat.generateViewId()

            setOnClickListener {

            }
        }
    }

    private val outerLinearLayout = LinearLayout(context).apply {
        id = ViewCompat.generateViewId()
        orientation = LinearLayout.VERTICAL
        weightSum = 5f

        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    private val innerLinearLayouts = (1..6).map {
        LinearLayout(context).apply {
            id = ViewCompat.generateViewId()
            orientation = LinearLayout.HORIZONTAL
            weightSum = 7f

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
            )
        }
    }

    private fun updateUIWithDate() {
        val compCalendar = nowCalendar.clone() as Calendar

        compCalendar.set(Calendar.DAY_OF_MONTH, 1)
        Log.d(" ", compCalendar.get(Calendar.DAY_OF_WEEK).toString())

        if(compCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
            nowCalendar.add(Calendar.DAY_OF_MONTH, -6)
        }
        else {
            compCalendar.add(Calendar.DAY_OF_MONTH, -3)
        }

        innerLinearLayouts.forEach {
            it.removeAllViews()
        }
        outerLinearLayout.removeAllViews()

        repeat(5) { row ->
            val innerLinearLayout = innerLinearLayouts[row]

            outerLinearLayout.addView(innerLinearLayout)

            repeat(7) { column ->
                val idx = row * 7 + column

                val item = dateItems[idx]
                if(compCalendar.isDaySame(nowCalendar)) {
                    item.tvDay.setTextColor(getColor(R.color.maco_orange))
                }
                if(!compCalendar.isMonthSame(nowCalendar)) {
                    item.tvDay.setTextColor(getColor(R.color.maco_light_gray))
                }
                item.tvDay.text = compCalendar.get(Calendar.DAY_OF_MONTH).toString()
                compCalendar.add(Calendar.DAY_OF_MONTH, 1)
                innerLinearLayout.addView(
                    item.root, LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f
                    )
                )
            }
        }
    }

    init {
        addView(outerLinearLayout)
        updateUIWithDate()
    }
}