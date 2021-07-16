package org.mascota.ui.view.rainbow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.mascota.data.remote.model.response.rainbow.ResFarewellSelect
import org.mascota.databinding.ItemFarewellBinding

class FarewellAdapter : RecyclerView.Adapter<FarewellAdapter.FarewellViewHolder>() {
    private var heroClickListener: ((String, Int) -> Unit)? = null
    private var isSelectedViewType = NOT_SELECTED
    private val _data = mutableListOf<ResFarewellSelect.Data.Pet>()
    var data: List<ResFarewellSelect.Data.Pet> = _data
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
        fun bind(data: ResFarewellSelect.Data.Pet, position: Int) {
            binding.apply {
                tvName.text = data.name
                Glide.with(ivHero).load(data.img).into(ivHero)
                clFarewell.isSelected = (position == isSelectedViewType)
                clFarewell.setOnClickListener {
                    it.isSelected = !it.isSelected
                    heroClickListener?.invoke(data.name, position)
                }
            }
        }
    }

    companion object {
        const val NOT_SELECTED = -1
    }
}