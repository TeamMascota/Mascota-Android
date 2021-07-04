package org.mascota.ui.view.custom.adapter

import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView
import org.mascota.ui.view.custom.CalendarView.Companion.firstPosition
import org.mascota.ui.view.custom.MonthView
import java.util.*

class CalendarViewPagerAdapter(private val calendar: Calendar) :
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
        holder.bind(position, calendar)
    }

    inner class CalendarViewHolder(private val view: MonthView) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, calendar: Calendar) {
            calendar.add(Calendar.MONTH, position - firstPosition)
        }
    }

    companion object {
        const val MAX_ITEM_COUNT = Int.MAX_VALUE
    }
}