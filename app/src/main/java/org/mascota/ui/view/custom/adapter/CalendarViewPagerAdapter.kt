package org.mascota.ui.view.custom.adapter

import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import org.mascota.data.remote.model.response.calendar.ResCalendar
import org.mascota.ui.view.calendar.data.model.CalendarData
import org.mascota.ui.view.custom.calendar.CalendarView.Companion.FIRST_POSITION
import org.mascota.ui.view.custom.calendar.MonthView
import java.util.*

class CalendarViewPagerAdapter(
    private val lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<CalendarViewPagerAdapter.CalendarViewHolder>() {
    //리스너 달기
    private val dateData = MutableLiveData<ResCalendar>()

    fun setDateData(data : ResCalendar) {
        dateData.value = data
    }

    override fun getItemCount(): Int = MAX_ITEM_COUNT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(MonthView(parent.context).apply {
            layoutParams = LayoutParams(
                MATCH_PARENT,
                MATCH_PARENT
            )
        })
    }

    override fun onBindViewHolder(
        holder: CalendarViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    inner class CalendarViewHolder(private val view: MonthView) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {

            view.setCalendarGetter {
                return@setCalendarGetter Calendar.getInstance(Locale.KOREA).apply {
                    set(Calendar.DAY_OF_MONTH, 1)
                    add(Calendar.MONTH, position - FIRST_POSITION)
                }
            }
            dateData.observe(lifecycleOwner) {
                view.setDateItemGetter {
                    return@setDateItemGetter it.data.calendar.date
                }
            }
        }
    }

    companion object {
        const val MAX_ITEM_COUNT = Int.MAX_VALUE
    }
}