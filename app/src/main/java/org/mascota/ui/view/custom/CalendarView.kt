package org.mascota.ui.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import org.mascota.R
import org.mascota.databinding.LayoutCalendarTopBinding
import org.mascota.ui.view.calendar.CalendarFragment
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
    var dateData: List<CalendarData> = _dateData
        set(value) {
            _dateData.clear()
            _dateData.addAll(value)
        }


    private val _curCalendar = MutableLiveData<Calendar>()
    private val curCalendar: LiveData<Calendar>
        get() = _curCalendar

    fun setCurCalendar(calendar: Calendar) {
        _curCalendar.postValue(calendar)
    }

    private var monthNextButtonClickListener: (() -> Unit)? = null
    private var monthPrevButtonClickListener: (() -> Unit)? = null
    private var yearNextButtonClickListener: (() -> Unit)? = null
    private var yearPrevButtonClickListener: (() -> Unit)? = null

    private val calendarViewPagerAdapter = CalendarViewPagerAdapter(
        listOf(
            CalendarData(CalendarFragment.DOG_ANGRY, "3", false),
            CalendarData(CalendarFragment.DOG_SAD, "0", true),
            CalendarData(CalendarFragment.DOG_USUAL, "0", true),
            CalendarData(CalendarFragment.EMPTY, "0", true),
            CalendarData(CalendarFragment.DOG_LOVE, "4", false),
            CalendarData(CalendarFragment.EMPTY, "0", true),
            CalendarData(CalendarFragment.DOG_BORING, "0", true),
            CalendarData(CalendarFragment.DOG_JOY, "3", false),
            CalendarData(CalendarFragment.EMPTY, "0", true),
            CalendarData(CalendarFragment.DOG_ANGRY, "3", false),
            CalendarData(CalendarFragment.EMPTY, "0", true),
            CalendarData(CalendarFragment.DOG_ANGRY, "3", false),
            CalendarData(CalendarFragment.DOG_ANGRY, "3", false),
            CalendarData(CalendarFragment.EMPTY, "0", true),
            CalendarData(CalendarFragment.DOG_LOVE, "0", true),
            CalendarData(CalendarFragment.DOG_ANGRY, "3", false)
        )
    )

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

    private fun setCalendar(calendar: Calendar) {
        setTopMonthText(calendar)
    }

    private fun initMonthChangeEvent() {
        var newPosition = 0
        layoutCalendarTopBinding.apply {
            ibMonthNext.setOnClickListener {
                monthNextButtonClickListener?.invoke()
                newPosition = calendarViewPager.currentItem + 1
                calendarViewPager.currentItem = newPosition
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibMonthPrev.setOnClickListener {
                monthPrevButtonClickListener?.invoke()
                newPosition = calendarViewPager.currentItem - 1
                calendarViewPager.currentItem = newPosition
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibYearNext.setOnClickListener {
                yearNextButtonClickListener?.invoke()
                newPosition = calendarViewPager.currentItem + 12
                calendarViewPager.currentItem = newPosition
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibYearPrev.setOnClickListener {
                yearPrevButtonClickListener?.invoke()
                newPosition = calendarViewPager.currentItem - 12
                calendarViewPager.currentItem = newPosition
                setCalendar(requireNotNull(curCalendar.value))
            }
        }
    }

    private fun createViewPager(): ViewPager2 {
        return ViewPager2(context, null).apply {
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            adapter = calendarViewPagerAdapter

            setCurrentItem(FIRST_POSITION, false)

            isUserInputEnabled = false
        }
    }

    private fun setTopMonthText(calendar: Calendar) {
        layoutCalendarTopBinding.tvYearMonth.text = convertCalendarToString(calendar)
    }

    fun setMonthNextButtonClickListener(listener: () -> Unit) {
        monthNextButtonClickListener = listener
    }

    fun setMonthPrevButtonClickListener(listener: () -> Unit) {
        monthPrevButtonClickListener = listener
    }

    fun setYearNextButtonClickListener(listener: () -> Unit) {
        yearNextButtonClickListener = listener
    }

    fun setYearPrevButtonClickListener(listener: () -> Unit) {
        yearPrevButtonClickListener = listener
    }

    companion object {
        const val FIRST_POSITION = MAX_ITEM_COUNT / 2
    }
}