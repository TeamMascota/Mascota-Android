package org.mascota.ui.view.content.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.data.remote.model.response.content.ResContentDetail
import org.mascota.databinding.ItemDiaryBinding
import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData
import org.mascota.util.StringUtil.setTextPartialBold

class ContentDetailDiaryAdapter :
    RecyclerView.Adapter<ContentDetailDiaryAdapter.ContentDetailDiaryViewHolder>() {

    private val _contentDiaryList = mutableListOf<ResContentDetail.Data.PetChapterDiary.Monthly.Diary>()

    private var diaryClickListener: ((String) -> Unit)? = null

    fun setDiaryClickListener(listener: (String) -> Unit) {
        diaryClickListener = listener
    }

    var contentDiaryList: List<ResContentDetail.Data.PetChapterDiary.Monthly.Diary> = _contentDiaryList
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
        fun onBind(diary: ResContentDetail.Data.PetChapterDiary.Monthly.Diary) {
            diary.apply{
                binding.contentDiaryInfoData = ContentDiaryInfoData(
                    date, weekday, feeling, title, contents, image
                )
                binding.tvDay.text = setTextPartialBold(
                    0,
                    date.length,
                    date + "\n" + weekday
                )
                binding.clItemDiary.setOnClickListener {
                    diaryClickListener?.invoke(diaryId)
                }
                binding.emo = Pair(kind, feeling)
            }
        }
    }

    companion object {
        const val IMAGE_RADIUS = 1
    }
}