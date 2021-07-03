package org.mascota.ui.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import org.mascota.R
import org.mascota.databinding.LayoutCalendarTopBinding
import org.mascota.util.CalendarUtil.convertCalendarToString
import java.util.*

class CalendarView(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val nowCalendar = Calendar.getInstance(Locale.KOREA)
    private val calendarTopView = createCalendarTopView()
    private lateinit var layoutCalendarTopBinding: LayoutCalendarTopBinding

    init {
        orientation = VERTICAL
        addView(calendarTopView)
        addView(createMonthView())
    }

    private fun createCalendarView() {
        addView(createCalendarTopView())
        addView(createMonthView())
        //addView(createViewPager())
    }

    private fun createCalendarTopView(): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        layoutCalendarTopBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_calendar_top, this, false)

        setTopMonthText(0)

        layoutCalendarTopBinding.ibMonthNext.setOnClickListener {
            //setViewPagerPosition(PREVIOUS_MONTH)
        }

        layoutCalendarTopBinding.ibMonthPrev.setOnClickListener {
            //setViewPagerPosition(NEXT_MONTH)
        }

        return layoutCalendarTopBinding.root
    }


    private fun createMonthView() : View {
        return MonthView(context, null).apply {
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    private val monthViewPagerGenerator = {
        ViewPager2(context).apply {

            //setCurrentItem(MonthlyAdapter.MAX_ITEM_COUNT, false)
            alpha = 0f

            setPageTransformer { page, position ->
                page.pivotX = if (position < 0) page.width.toFloat() else 0f
                page.pivotY = page.height * 0.5f
                page.rotationY = 25f * position
            }

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                /*override fun onPageSelected(position: Int) {
                    val (_, firstDateOfMonth) = convertMonthlyIndexToDateToFirstDateOfMonthCalendar(
                        position
                    )
                    if (isExpanded && curDate != firstDateOfMonth) {
                        curDate = firstDateOfMonth
                    }
                }*/
            })

            offscreenPageLimit = 1
        }
    }

    private fun setTopMonthText(move: Int) {
        val calendar = nowCalendar.clone() as Calendar

        calendar.apply {
            add(Calendar.MONTH, move)
        }

        layoutCalendarTopBinding.tvYearMonth.text = convertCalendarToString(calendar)
    }

    private var monthlyViewPager: ViewPager2? = null
}