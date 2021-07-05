package org.mascota.ui.view.diary.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.mascota.R
import org.mascota.databinding.ItemEmotionBinding
import org.mascota.ui.view.diary.data.EmotionList

class SelectEmotionAdapter : RecyclerView.Adapter<SelectEmotionAdapter.SelectEmotionViewHolder>(){



    var emotionList = mutableListOf<EmotionList>()

    class SelectEmotionViewHolder(
        private val viewBiding : ItemEmotionBinding

    ) : RecyclerView.ViewHolder(
        viewBiding.root
    ){
        fun onBind(data : EmotionList){
            viewBiding.emotion = data
            viewBiding.tvPetName.setText(data.pet_name)

           with(viewBiding) {
               ivDogAngry.setOnClickListener {
                   ivDogAngry.isSelected = !it.isSelected
               }
               ivDogBoring.setOnClickListener {
                   ivDogBoring.isSelected = !it.isSelected
               }
               ivDogJoy.setOnClickListener {
                   ivDogJoy.isSelected = !it.isSelected
               }
               ivDogLove.setOnClickListener {
                   ivDogLove.isSelected = !it.isSelected
               }
               ivDogSad.setOnClickListener {
                   ivDogSad.isSelected = !it.isSelected
               }
               ivDogUsual.setOnClickListener {
                   ivDogUsual.isSelected = !it.isSelected
               }
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectEmotionViewHolder {
        val binding : ItemEmotionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_emotion, parent,false)

        return SelectEmotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectEmotionViewHolder, position: Int) {
        holder.onBind(emotionList[position])
    }

    override fun getItemCount(): Int = emotionList.size
}
//삭제버튼  클릭 리스너
// 감정선택시 -> 검은색 테두리 : 셀렉터