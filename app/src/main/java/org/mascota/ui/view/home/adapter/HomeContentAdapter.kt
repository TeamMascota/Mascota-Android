package org.mascota.ui.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemContentBinding
import org.mascota.ui.view.home.data.model.HomeContentInfoData

class HomeContentAdapter : RecyclerView.Adapter<HomeContentAdapter.HomeContentViewHolder>(){

    val contentList = mutableListOf<HomeContentInfoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeContentViewHolder {
        val binding = ItemContentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeContentViewHolder, position: Int) {
        holder.onBind(contentList[position])
    }

    override fun getItemCount(): Int = contentList.size

    class HomeContentViewHolder(
        private val binding : ItemContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(homeContentData : HomeContentInfoData) {
            binding.homeContentInfoData = homeContentData
        }
    }
}