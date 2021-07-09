package org.mascota.ui.view.rainbow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemMomentBinding
import org.mascota.ui.view.rainbow.data.model.RainbowInfoData

class BestMomentAdapter : RecyclerView.Adapter<BestMomentAdapter.BestMomentViewHolder>(){

    private val _lovedata = mutableListOf<RainbowInfoData.Data>()
    var data : List<RainbowInfoData.Data> = _lovedata

    set(value) {

        _lovedata.clear()
        _lovedata.addAll(value)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestMomentViewHolder {

        val binding = ItemMomentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return BestMomentViewHolder(binding)

    }

    override fun onBindViewHolder(holder: BestMomentViewHolder, position: Int) {
        holder.bind(_lovedata[position])

    }

    override fun getItemCount(): Int = _lovedata.size

    inner class BestMomentViewHolder(private val binding : ItemMomentBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(rainbowInfoData: RainbowInfoData.Data){

                binding.dvBestMoment.setWhereBookView(IS_RAINBOW)
                binding.dvBestMoment.setLeftRainbow(rainbowInfoData)
                binding.dvBestMoment.setRightRainbow(rainbowInfoData)

            }



    }

    companion object {
        const val IS_RAINBOW = false
    }



}