package org.mascota.ui.view.rainbow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemFarewellBinding

class FarewellAdapter : RecyclerView.Adapter<FarewellAdapter.FarewellViewHolder>() {
    private var heroClickListener: (() -> Unit) ?= null
    private val _data = mutableListOf<String>()
    var data: List<String> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    fun setHeroClickListener(listener : () -> Unit) {
        this.heroClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarewellViewHolder {
        val binding = ItemFarewellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return FarewellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FarewellViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size

    inner class FarewellViewHolder(private val binding: ItemFarewellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name : String) {
            binding.apply {
                tvName.text = name
                clFarewell.setOnClickListener {
                    it.isSelected = !it.isSelected
                    heroClickListener?.invoke()
                }
            }
        }
    }
}