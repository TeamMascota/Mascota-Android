package org.mascota.ui.view.diary.read.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.data.remote.model.response.diary.ResDiaryRead
import org.mascota.databinding.ItemFeelingBinding
import org.mascota.ui.view.diary.read.data.model.DiaryEmoDataInfo
import org.mascota.ui.view.diary.read.data.model.DiaryPetFeelingInfoData

class EmotionImageAdapter() : RecyclerView.Adapter<EmotionImageAdapter.EmotionImgViewHolder>() {

    private val _emotionNumList = mutableListOf<DiaryEmoDataInfo>()

    var emotionList: List<DiaryEmoDataInfo> = _emotionNumList
        set(value) {
            _emotionNumList.clear()
            _emotionNumList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionImgViewHolder {
        val binding = ItemFeelingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EmotionImgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmotionImgViewHolder, position: Int) {
        holder.onBind(_emotionNumList[position])
    }

    override fun getItemCount(): Int = emotionList.size

    private var preEmoList = mutableListOf<Pair<Int, Int>>()

    inner class EmotionImgViewHolder(
        val binding: ItemFeelingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(diaryEmoData: DiaryEmoDataInfo) {
            binding.apply {
                with(diaryEmoData) {
                    emoBigType = Pair(feeling, kind)
                    smallProfileVisibility = false
                    ProfileAdapter().apply {
                        binding.rvProfile.adapter = this
                        profileUrlList = imgs
                    }
                }
            }
            binding.ivEmotion.setOnClickListener {
                if(diaryEmoData.kind != 0) {
                    binding.smallProfileVisibility = !requireNotNull(binding.smallProfileVisibility)
                }
            }
        }
    }
}