package org.mascota.ui.view.content.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemDiaryBinding
import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData

class ContentDetailDiaryAdapter: RecyclerView.Adapter<ContentDetailDiaryAdapter.ContentDetailDiaryViewHolder>() {

    private val _contentDiaryList = mutableListOf<ContentDiaryInfoData>()

    var contentDiaryList: List<ContentDiaryInfoData> = _contentDiaryList
        set(value) {
            _contentDiaryList.clear()
            _contentDiaryList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentDetailDiaryViewHolder {
        val binding = ItemDiaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentDetailDiaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentDetailDiaryViewHolder, position: Int) {
        holder.onBind(_contentDiaryList[position])
    }

    override fun getItemCount(): Int = contentDiaryList.size

    class ContentDetailDiaryViewHolder(
        private val binding: ItemDiaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(contentDiaryInfoData: ContentDiaryInfoData) {
            binding.contentDiaryInfoData = contentDiaryInfoData
        }
    }
}