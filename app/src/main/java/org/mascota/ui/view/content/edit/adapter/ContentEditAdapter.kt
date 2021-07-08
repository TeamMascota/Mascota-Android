package org.mascota.ui.view.content.edit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemContentEditBinding
import org.mascota.ui.view.content.edit.data.model.ContentEditInfoData

class ContentEditAdapter : RecyclerView.Adapter<ContentEditAdapter.ContentEditViewHolder>() {

    private val _contentEditList = mutableListOf<ContentEditInfoData>()

    var contentEditList: List<ContentEditInfoData> = _contentEditList
        set(value) {
            _contentEditList.clear()
            _contentEditList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentEditViewHolder {
        val binding = ItemContentEditBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentEditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentEditViewHolder, position: Int) {
        holder.onBind(_contentEditList[position])
    }

    override fun getItemCount(): Int = contentEditList.size

    class ContentEditViewHolder(
        private val binding: ItemContentEditBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(contentEditInfoData: ContentEditInfoData) {
            binding.contentEditInfoData = contentEditInfoData
        }
    }
}