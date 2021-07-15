package org.mascota.ui.view.diary.read.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.R
import org.mascota.databinding.LayoutPetImageBinding
import org.mascota.ui.view.diary.read.DiaryReadActivity

class PetImagePagerAdapter : RecyclerView.Adapter<PetImagePagerAdapter.PetImagePagerViewHolder>() {

    private val _petImgUrlList = mutableListOf<String>()

    var pagerFrom : Int = 0

    var petImgUrlList: List<String> = _petImgUrlList
        set(value) {
            _petImgUrlList.clear()
            _petImgUrlList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetImagePagerViewHolder {
        val binding = LayoutPetImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PetImagePagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetImagePagerViewHolder, position: Int) {
        holder.onBind(_petImgUrlList[position])
    }

    override fun getItemCount(): Int = petImgUrlList.size

    inner class PetImagePagerViewHolder(
        private val binding: LayoutPetImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(petImgUrl: String) {
           binding.imgUrl = petImgUrl
            binding.clOutLine.apply{
                if(pagerFrom == DiaryReadActivity.IS_BEST_MOMENT){
                    setBackgroundResource(R.drawable.rectangle_blue_radius_0)
                }else {
                    setBackgroundResource(R.drawable.rectangle_orange_radius_0)
                }
            }
        }
    }
}