package org.mascota.ui.view.rainbow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemHelpMessageBinding
import org.mascota.ui.view.rainbow.data.model.HelpInfoData

class HelpAdapter: RecyclerView.Adapter<HelpAdapter.HelpViewHolder>() {
    private var helpMessageClickListener: ((String) -> Unit) ?= null
    private val _data = mutableListOf<HelpInfoData>()
    var data: List<HelpInfoData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    fun setHelpMessageClickListener(listener : (String) -> Unit) {
        this.helpMessageClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val binding = ItemHelpMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return HelpViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size

    inner class HelpViewHolder(private val binding: ItemHelpMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(helpInfoData : HelpInfoData) {
            binding.apply {
                tvContent.text = helpInfoData.content
                clHelp.setOnClickListener { helpMessageClickListener?.invoke(helpInfoData.url) }
            }
        }
    }
}