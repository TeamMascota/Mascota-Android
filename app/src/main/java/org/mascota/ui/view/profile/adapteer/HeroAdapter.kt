package org.mascota.ui.view.profile.adapteer

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.mascota.databinding.ItemHeroPetBinding
import org.mascota.ui.view.profile.data.model.ResHero
import org.mascota.util.StringUtil.makeHeroNumbering

class HeroAdapter : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {
    private var itemClickListener: ((Int, Boolean) -> Unit) ?= null
    private var quitBtnClickListener: ((Int) -> Unit) ?= null
    private var isSelectedViewType = 0
    private val _data = mutableListOf<Uri?>()
    var data: List<Uri?> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    fun setItemViewType(type : Int) {
        isSelectedViewType = type
    }

    override fun getItemViewType(position: Int): Int {
        return isSelectedViewType
    }

    fun setItemClickListener(listener : (Int, Boolean)-> Unit) {
        this.itemClickListener = listener
    }

    fun setQuitBtnClickListener(listener : (position: Int)-> Unit) {
        this.quitBtnClickListener = listener
    }

    override fun getItemCount(): Int = _data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding: ItemHeroPetBinding = ItemHeroPetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(_data[position], position)
    }

    inner class HeroViewHolder(private val binding: ItemHeroPetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Uri?, position: Int) {
            binding.apply {
                if(position == FIRST_HERO)
                    layoutEmptyImage.ibQuit.visibility = View.INVISIBLE

                clItem.isSelected = position == isSelectedViewType

                tvHero.text = makeHeroNumbering(position + 1)
                clItem.setOnClickListener {
                    itemClickListener?.invoke(position, clItem.isSelected)
                }

                if(data != null) {
                    Glide.with(ivPetImg).load(data).into(ivPetImg)
                    layoutEmptyImage.ivEmptyImg.isVisible = false
                }

                layoutEmptyImage.ibQuit.setOnClickListener {
                    quitBtnClickListener?.invoke(position)
                }
            }
        }
    }

    companion object {
        const val FIRST_HERO = 0
    }
}