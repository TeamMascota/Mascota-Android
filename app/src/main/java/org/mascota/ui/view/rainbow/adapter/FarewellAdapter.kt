package org.mascota.ui.view.rainbow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemFarewellBinding

class FarewellAdapter : RecyclerView.Adapter<FarewellAdapter.FarewellViewHolder>() {
    private var heroClickListener: ((String, Int) -> Unit)? = null
    private var isSelectedViewType = NOT_SELECTED
    private val _data = mutableListOf<String>()
    var data: List<String> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    fun setHeroClickListener(listener: (String, Int) -> Unit) {
        this.heroClickListener = listener
    }

    fun setItemViewType(type : Int) {
        isSelectedViewType = type
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return isSelectedViewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarewellViewHolder {
        val binding = ItemFarewellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return FarewellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FarewellViewHolder, position: Int) {
        holder.bind(_data[position], position)
    }

    override fun getItemCount(): Int = _data.size

    inner class FarewellViewHolder(private val binding: ItemFarewellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, position: Int) {
            binding.apply {
                tvName.text = name
                clFarewell.isSelected = (position == isSelectedViewType)
                clFarewell.setOnClickListener {
                    it.isSelected = !it.isSelected
                    heroClickListener?.invoke(name, position)
                }
            }
        }
    }

    companion object {
        const val NOT_SELECTED = -1
    }
}