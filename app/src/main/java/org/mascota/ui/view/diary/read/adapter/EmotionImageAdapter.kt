package org.mascota.ui.view.diary.read.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemImageBinding

class EmotionImageAdapter : RecyclerView.Adapter<EmotionImageAdapter.EmotionImgViewHolder>() {

    private val _emotionNumList = mutableListOf<Int>()

    var emotionNumList: List<Int> = _emotionNumList
        set(value) {
            _emotionNumList.clear()
            _emotionNumList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionImgViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EmotionImgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmotionImgViewHolder, position: Int) {
        holder.onBind(_emotionNumList[position])
    }

    override fun getItemCount(): Int = emotionNumList.size

    class EmotionImgViewHolder(
        private val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(emotionImgNum: Int) {
            binding.emotionImgNum = emotionImgNum
        }
    }
}