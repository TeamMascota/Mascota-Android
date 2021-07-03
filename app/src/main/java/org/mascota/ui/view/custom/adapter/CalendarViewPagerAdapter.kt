package org.mascota.ui.view.custom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.ui.view.custom.MonthView

class CalendarViewPagerAdapter: RecyclerView.Adapter<CalendarViewPagerAdapter.CalendarViewHolder>() {
    //리스너 달기
    override fun getItemCount(): Int = MAX_ITEM_COUNT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {

        return CalendarViewHolder(MonthView(parent.context))
    }

    override fun onBindViewHolder(holder: CalendarViewPagerAdapter.CalendarViewHolder, position: Int) {
        holder.bind(position)
    }

    class CalendarViewHolder(private val view: MonthView) : RecyclerView.ViewHolder(view) {
        fun bind(int : Int) {
            //view.data = data.value?.get(firstDateOfMonth.yearMonthFormat)
        }
    }

    companion object {
        const val MAX_ITEM_COUNT = Int.MAX_VALUE
    }
}