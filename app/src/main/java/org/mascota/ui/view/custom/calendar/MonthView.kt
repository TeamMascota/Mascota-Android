package org.mascota.ui.view.custom.calendar

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import org.mascota.R
import org.mascota.data.remote.model.response.calendar.ResCalendar
import org.mascota.databinding.ItemDateBinding
import org.mascota.ui.view.calendar.CalendarFragment.Companion.EMPTY
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.view.diary.read.DiaryReadActivity
import org.mascota.util.BindingAdapter.TODAY_EMPTY
import org.mascota.util.CalendarUtil.isDaySame
import org.mascota.util.extension.getColor
import java.util.*

class MonthView(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val emptyTodayData = ResCalendar.Data.Calendar.Date(EMPTY, EMPTY, listOf(), TODAY_EMPTY)
    private val emptyData = ResCalendar.Data.Calendar.Date(EMPTY, EMPTY, listOf(), EMPTY)
    private var dateItemGetter: (() -> List<ResCalendar.Data.Calendar.Date>)? = null
    private var calendarGetter: (() -> Calendar)? = null
    private val nowCalendar = Calendar.getInstance(Locale.KOREA)

    private val dateItems = (1..42).map { _ ->
        ItemDateBinding.inflate(LayoutInflater.from(context), null, false).apply {
            root.id = ViewCompat.generateViewId()
        }
    }

    fun setCalendarGetter(listener: () -> Calendar) {
        calendarGetter = listener
    }

    fun setDateItemGetter(listener: () -> List<ResCalendar.Data.Calendar.Date>) {
        dateItemGetter = listener
    }

    private val outerLinearLayout = LinearLayout(context).apply {
        id = ViewCompat.generateViewId()
        orientation = LinearLayout.VERTICAL
        weightSum = 6f

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

    init {
        addView(outerLinearLayout)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        updateUIWithDate()
    }

    private fun updateUIWithDate() {
        var pos = 0
        var index = 31
        val dateItem = dateItemGetter?.invoke()
        val compCalendar = calendarGetter?.invoke()?.clone() as Calendar
        val curMonth = compCalendar.get(Calendar.MONTH)


        if (dateItem != null) {
            index = dateItem.size
        }

        compCalendar.set(Calendar.DAY_OF_MONTH, 1)
        if (compCalendar.get(Calendar.DAY_OF_WEEK) == 1)
            compCalendar.add(Calendar.DAY_OF_MONTH, -6)
        else
            compCalendar.add(Calendar.DAY_OF_MONTH, 2 - compCalendar.get(Calendar.DAY_OF_WEEK))

        innerLinearLayouts.forEach {
            it.removeAllViews()
        }
        outerLinearLayout.removeAllViews()

        repeat(6) { row ->
            val innerLinearLayout = innerLinearLayouts[row]

            outerLinearLayout.addView(innerLinearLayout)

            repeat(7) { column ->
                val idx = row * 7 + column
                val item = dateItems[idx]
                if (compCalendar.get(Calendar.MONTH) != curMonth) {
                    item.tvDay.setTextColor(getColor(R.color.maco_light_gray))
                    item.dateItem = emptyData
                    item.isEmpty = true
                } else {
                    if (compCalendar.isDaySame(nowCalendar)) {
                        item.tvDay.setTextColor(getColor(R.color.maco_orange))
                        if(pos < index) {
                            val data = dateItem?.get(pos++)
                            if (data != null) {
                                if(data.id.size > 1) {
                                    item.isEmpty = false
                                    item.tvRecordSize.text = data.id.size.toString()
                                }
                                else
                                    item.isEmpty = true
                                item.dateItem = data
                                item.ivEmo.setOnClickListener {
                                    startActivity(context, Intent(context, DiaryReadActivity::class.java), null)
                                }
                            }
                            else {
                                item.dateItem = emptyTodayData
                                item.isEmpty = true
                                item.ivEmo.setOnClickListener {
                                    startActivity(context, Intent(context, DiaryWriteActivity::class.java), null)
                                }
                            }
                        }
                    } else {
                        item.tvDay.setTextColor(getColor(R.color.maco_black))
                        if(pos < index) {
                            val data = dateItem?.get(pos++)
                            if (data != null) {
                                if(data.id.size > 1) {
                                    item.isEmpty = false
                                    item.tvRecordSize.text = data.id.size.toString()
                                }
                                else
                                    item.isEmpty = true
                                item.dateItem = data
                                item.ivEmo.setOnClickListener {
                                    startActivity(context, Intent(context, DiaryReadActivity::class.java), null)
                                }
                            }
                            else {
                                item.dateItem = emptyData
                                item.isEmpty = true
                            }
                        }
                    }
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
}