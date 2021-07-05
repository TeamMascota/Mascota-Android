package org.mascota.ui.view.custom.adapter

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView
import org.mascota.ui.view.calendar.data.model.CalendarData
import org.mascota.ui.view.custom.CalendarView.Companion.FIRST_POSITION
import org.mascota.ui.view.custom.MonthView
import java.util.*

class CalendarViewPagerAdapter(private val dateItem: List<CalendarData>) :
    RecyclerView.Adapter<CalendarViewPagerAdapter.CalendarViewHolder>() {
    //리스너 달기
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
            view.setDateItemGetter {
                return@setDateItemGetter dateItem
            }
        }
    }

    companion object {
        const val MAX_ITEM_COUNT = Int.MAX_VALUE
    }
}