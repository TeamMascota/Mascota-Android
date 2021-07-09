package org.mascota.ui.view.diary.read.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.LayoutPetImageBinding

class PetImagePagerAdapter : RecyclerView.Adapter<PetImagePagerAdapter.PetImagePagerViewHolder>() {

    private val _petImgUrlList = mutableListOf<String>()

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

    class PetImagePagerViewHolder(
        private val binding: LayoutPetImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(petImgUrl: String) {
           binding.imgUrl = petImgUrl
        }
    }
}