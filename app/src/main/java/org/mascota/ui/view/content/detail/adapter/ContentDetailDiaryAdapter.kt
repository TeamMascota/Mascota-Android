package org.mascota.ui.view.content.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.mascota.databinding.ItemDiaryBinding
import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData
import org.mascota.util.StringUtil.setTextPartialBold
import org.mascota.util.dp

class ContentDetailDiaryAdapter :
    RecyclerView.Adapter<ContentDetailDiaryAdapter.ContentDetailDiaryViewHolder>() {

    private val _contentDiaryList = mutableListOf<ContentDiaryInfoData>()

    private var diaryClickListener: (() -> Unit)? = null

    fun setDiaryClickListener(listener: () -> Unit) {
        diaryClickListener = listener
    }

    var contentDiaryList: List<ContentDiaryInfoData> = _contentDiaryList
        set(value) {
            _contentDiaryList.clear()
            _contentDiaryList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentDetailDiaryViewHolder {
        val binding = ItemDiaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentDetailDiaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentDetailDiaryViewHolder, position: Int) {
        holder.onBind(_contentDiaryList[position])
    }

    override fun getItemCount(): Int = contentDiaryList.size

    inner class ContentDetailDiaryViewHolder(
        val binding: ItemDiaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(contentDiaryInfoData: ContentDiaryInfoData) {
            binding.contentDiaryInfoData = contentDiaryInfoData
            binding.tvDay.text = setTextPartialBold(
                0,
                contentDiaryInfoData.date.length,
                contentDiaryInfoData.date + "\n" + contentDiaryInfoData.weekDay
            )
            binding.clItemDiary.setOnClickListener {
                diaryClickListener?.invoke()
            }
        }
    }

    companion object {
        const val IMAGE_RADIUS = 3
    }
}