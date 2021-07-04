package org.mascota.ui.view.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.transition.TransitionManager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.*
import org.mascota.R
import org.mascota.data.local.MascotaPreference.setMonth
import org.mascota.data.local.MascotaPreference.setYear
import org.mascota.databinding.LayoutCalendarTopBinding
import org.mascota.ui.view.calendar.data.model.CalendarData
import org.mascota.ui.view.custom.adapter.CalendarViewPagerAdapter
import org.mascota.ui.view.custom.adapter.CalendarViewPagerAdapter.Companion.MAX_ITEM_COUNT
import org.mascota.util.CalendarUtil.convertCalendarToString
import org.mascota.util.CalendarUtil.initCalendar
import java.util.*

class CalendarView(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val _dateData = mutableListOf<CalendarData>()
    var dateData : List<CalendarData> = _dateData
        set(value) {
            _dateData.clear()
            _dateData.addAll(value)
        }

    private val _curCalendar = MutableLiveData<Calendar>()
    private val curCalendar: LiveData<Calendar>
        get() = _curCalendar

    fun setCurCalendar(calendar: Calendar) { _curCalendar.postValue(calendar) }

    private var monthNextButtonClickListener: (()-> Unit) ?= null
    private var monthPrevButtonClickListener: (()-> Unit) ?= null
    private var yearNextButtonClickListener: (()-> Unit) ?= null
    private var yearPrevButtonClickListener: (()-> Unit) ?= null

    private val calendarTopView
        get() = createCalendarTopView()
    private val calendarViewPager = createViewPager()

    private lateinit var layoutCalendarTopBinding: LayoutCalendarTopBinding

    init {
        orientation = VERTICAL
        addView(calendarTopView)
        addView(calendarViewPager)
    }

    private fun createCalendarTopView(): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        layoutCalendarTopBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_calendar_top, this, false)

        setCalendar(initCalendar(Calendar.getInstance(Locale.KOREA)))

        initMonthChangeEvent()

        return layoutCalendarTopBinding.root
    }

    private fun setCalendar(calendar : Calendar) {
        setMonth(calendar)
        setYear(calendar)
        setTopMonthText(calendar)
    }

    private fun initMonthChangeEvent() {
        layoutCalendarTopBinding.apply {
            ibMonthNext.setOnClickListener {
                monthNextButtonClickListener?.invoke()
                curItem += 1
                calendarViewPager.setCurrentItem(curItem,true)
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibMonthPrev.setOnClickListener {
                monthPrevButtonClickListener?.invoke()
                curItem -= 1
                calendarViewPager.setCurrentItem(curItem,true)
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibYearNext.setOnClickListener {
                yearNextButtonClickListener?.invoke()
                curItem += 12
                calendarViewPager.setCurrentItem(curItem,true)
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibYearPrev.setOnClickListener {
                yearPrevButtonClickListener?.invoke()
                curItem -= 12
                calendarViewPager.setCurrentItem(curItem,true)
                setCalendar(requireNotNull(curCalendar.value))
            }
        }
    }

    private fun createViewPager() : ViewPager2 {
        return ViewPager2(context, null).apply {
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            adapter = CalendarViewPagerAdapter(initCalendar(Calendar.getInstance(Locale.KOREA)))

            setCurrentItem(firstPosition,false)

            isUserInputEnabled = false
        }
    }

    private fun setTopMonthText(calendar : Calendar) {
        layoutCalendarTopBinding.tvYearMonth.text = convertCalendarToString(calendar)
    }

    fun setMonthNextButtonClickListener(listener : ()-> Unit) {
        monthNextButtonClickListener = listener
    }

    fun setMonthPrevButtonClickListener(listener : ()-> Unit) {
        monthPrevButtonClickListener = listener
    }

    fun setYearNextButtonClickListener(listener : ()-> Unit) {
        yearNextButtonClickListener = listener
    }

    fun setYearPrevButtonClickListener(listener : ()-> Unit) {
        yearPrevButtonClickListener = listener
    }

    companion object {
        const val firstPosition = MAX_ITEM_COUNT / 2
        var curItem = firstPosition
    }
}