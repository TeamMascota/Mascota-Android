package org.mascota.ui.view.diary.read.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemFeelingBinding
import org.mascota.ui.view.diary.read.data.model.DiaryPetFeelingInfoData

class EmotionImageAdapter() : RecyclerView.Adapter<EmotionImageAdapter.EmotionImgViewHolder>() {

    private val _emotionNumList = mutableListOf<DiaryPetFeelingInfoData>()

    var emotionNumList: List<DiaryPetFeelingInfoData> = _emotionNumList
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

    override fun getItemCount(): Int = emotionNumList.size

    class EmotionImgViewHolder(
        val binding: ItemFeelingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(diaryPetFeelingInfoData: DiaryPetFeelingInfoData) {
            binding.apply {
                with(diaryPetFeelingInfoData) {
                    emotionImgNum = emotion
                    smallProfileVisibility = false
                }
            }
            ProfileAdapter().apply {
                binding.rvProfile.adapter = this
                profileUrlList = diaryPetFeelingInfoData.profileUrlList
            }
            binding.ivEmotion.setOnClickListener {
                binding.smallProfileVisibility = !requireNotNull(binding.smallProfileVisibility)
            }
        }
    }
}