package org.mascota.ui.view.content.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemDiaryMonthBinding
import org.mascota.ui.view.content.detail.data.model.ContentMonthInfoData

class ContentDetailMonthAdapter : RecyclerView.Adapter<ContentDetailMonthAdapter.ContentDetailMonthViewHolder>() {

    private val _contentMonthList = mutableListOf<ContentMonthInfoData>()

    var contentMonthList: List<ContentMonthInfoData> = _contentMonthList
        set(value) {
            _contentMonthList.clear()
            _contentMonthList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentDetailMonthViewHolder {
        val binding = ItemDiaryMonthBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentDetailMonthViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentDetailMonthViewHolder, position: Int) {
        holder.onBind(contentMonthList[position])
        contentDetailDiaryAdapter = ContentDetailDiaryAdapter()//
        holder.binding.rvContentDiary.adapter = contentDetailDiaryAdapter//
    }

    override fun getItemCount(): Int = contentMonthList.size

    private lateinit var contentDetailDiaryAdapter: ContentDetailDiaryAdapter //

    class ContentDetailMonthViewHolder(
        val binding: ItemDiaryMonthBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(contentMonthInfoData: ContentMonthInfoData) {
            binding.contentMonthDataInfo = contentMonthInfoData
        }
    }
}