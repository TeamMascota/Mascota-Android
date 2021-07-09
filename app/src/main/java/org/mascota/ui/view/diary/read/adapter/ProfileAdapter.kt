package org.mascota.ui.view.diary.read.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemSmallProfileBinding

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.TestViewHolder>() {

    private val _profileUrlList = mutableListOf<String>()

    var profileUrlList: List<String> = _profileUrlList
        set(value) {
            _profileUrlList.clear()
            _profileUrlList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val binding = ItemSmallProfileBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.onBind(_profileUrlList[position])
    }

    override fun getItemCount(): Int = profileUrlList.size

    class TestViewHolder(
        private val binding: ItemSmallProfileBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(profileImgUrl: String) {
            binding.profileImgUrl = profileImgUrl
        }
    }
}