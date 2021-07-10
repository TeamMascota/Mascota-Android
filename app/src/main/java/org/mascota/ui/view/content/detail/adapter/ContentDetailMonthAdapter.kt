package org.mascota.ui.view.content.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemDiaryMonthBinding
import org.mascota.ui.view.content.detail.data.model.ContentMonthInfoData
import org.mascota.util.dp

class ContentDetailMonthAdapter :
    RecyclerView.Adapter<ContentDetailMonthAdapter.ContentDetailMonthViewHolder>() {

    private val _contentMonthList = mutableListOf<ContentMonthInfoData>()

    var contentMonthList: List<ContentMonthInfoData> = _contentMonthList
        set(value) {
            _contentMonthList.clear()
            _contentMonthList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentDetailMonthViewHolder {
        val binding = ItemDiaryMonthBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentDetailMonthViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentDetailMonthViewHolder, position: Int) {
        holder.onBind(_contentMonthList[position])


    }

    override fun getItemCount(): Int = contentMonthList.size

    inner class ContentDetailMonthViewHolder(
        val binding: ItemDiaryMonthBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ClickableViewAccessibility")
        fun onBind(contentMonthInfoData: ContentMonthInfoData) {
            binding.contentMonthDataInfo = contentMonthInfoData

            ContentDetailDiaryAdapter().apply {
                contentDiaryList = contentMonthInfoData.diaryList
                binding.rvContentDiary.adapter = this
                val swipeHelperCallback = SwipeHelperCallback()
                swipeHelperCallback.apply {
                    setClamp(144.dp.toFloat()) // swipe fix size
                }

                val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
                itemTouchHelper.attachToRecyclerView(binding.rvContentDiary)
                binding.rvContentDiary.apply {
                    setOnTouchListener { _, _ ->
                        swipeHelperCallback.removePreviousClamp(this)
                        false
                    }
                }

            }
        }
    }
}