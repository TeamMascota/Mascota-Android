package org.mascota.ui.view.custom.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.transition.TransitionManager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.*
import org.mascota.R
import org.mascota.data.remote.model.response.calendar.ResCalendar
import org.mascota.databinding.LayoutCalendarTopBinding
import org.mascota.ui.view.custom.adapter.CalendarViewPagerAdapter
import org.mascota.ui.view.custom.adapter.CalendarViewPagerAdapter.Companion.MAX_ITEM_COUNT
import org.mascota.util.CalendarUtil.convertCalendarToString
import org.mascota.util.CalendarUtil.initCalendar
import java.util.*

class CalendarView(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val _curCalendar = MutableLiveData<Calendar>()
    private val curCalendar: LiveData<Calendar>
        get() = _curCalendar

    private val calendarData = MutableLiveData<ResCalendar>()

    fun setCalendarData(data: ResCalendar) {
        calendarData.postValue(data)
    }

    private fun setAdapterData(data : ResCalendar) {
        calendarViewPagerAdapter.setDateData(data)
        calendarViewPagerAdapter.notifyDataSetChanged()
    }

    fun setCurCalendar(calendar: Calendar) {
        _curCalendar.postValue(calendar)
    }

    private val fragmentViewLifecycleOwner
        get() = findFragment<Fragment>().viewLifecycleOwner

    private var monthNextButtonClickListener: (() -> Unit)? = null
    private var monthPrevButtonClickListener: (() -> Unit)? = null
    private var yearNextButtonClickListener: (() -> Unit)? = null
    private var yearPrevButtonClickListener: (() -> Unit)? = null

    private lateinit var calendarViewPagerAdapter : CalendarViewPagerAdapter

    private val calendarTopView
        get() = createCalendarTopView()
    private lateinit var calendarViewPager : ViewPager2

    private lateinit var layoutCalendarTopBinding: LayoutCalendarTopBinding

    init {
        orientation = VERTICAL
        addView(calendarTopView)
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
        var newPosition: Int
        layoutCalendarTopBinding.apply {
            ibMonthNext.setOnClickListener {
                monthNextButtonClickListener?.invoke()
                newPosition = calendarViewPager.currentItem + 1
                calendarViewPager.currentItem = newPosition
                //setAdapterData(requireNotNull(calendarData))
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibMonthPrev.setOnClickListener {
                monthPrevButtonClickListener?.invoke()
                newPosition = calendarViewPager.currentItem - 1
                calendarViewPager.currentItem = newPosition
                //setAdapterData(requireNotNull(calendarData))
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibYearNext.setOnClickListener {
                yearNextButtonClickListener?.invoke()
                newPosition = calendarViewPager.currentItem + 12
                calendarViewPager.currentItem = newPosition
                //setAdapterData(requireNotNull(calendarData))
                setCalendar(requireNotNull(curCalendar.value))
            }
            ibYearPrev.setOnClickListener {
                yearPrevButtonClickListener?.invoke()
                newPosition = calendarViewPager.currentItem - 12
                calendarViewPager.currentItem = newPosition
                //setAdapterData(requireNotNull(calendarData))
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

    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private lateinit var lazyPagerAddJob: Job
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lazyPagerAddJob = scope.launch {
            calendarData.toString()
            calendarViewPagerAdapter = CalendarViewPagerAdapter(fragmentViewLifecycleOwner)
            calendarViewPager = createViewPager()
            TransitionManager.beginDelayedTransition(this@CalendarView)
            addView(calendarViewPager)
            calendarData.observe(fragmentViewLifecycleOwner){
                setAdapterData(it)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lazyPagerAddJob.cancel()
    }

    companion object {
        const val FIRST_POSITION = MAX_ITEM_COUNT / 2
    }
}