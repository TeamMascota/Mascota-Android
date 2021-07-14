package org.mascota.ui.view.rainbow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.data.remote.model.response.rainbow.ResBestMoment
import org.mascota.databinding.ItemMomentBinding

class BestMomentAdapter : RecyclerView.Adapter<BestMomentAdapter.BestMomentViewHolder>() {


    private val _data = mutableListOf<Pair<ResBestMoment.Data.TheBestMoment, ResBestMoment.Data.TheBestMoment>>()
    var data: List<Pair<ResBestMoment.Data.TheBestMoment, ResBestMoment.Data.TheBestMoment>> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestMomentViewHolder {

        val binding = ItemMomentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return BestMomentViewHolder(binding)

    }

    override fun onBindViewHolder(holder: BestMomentViewHolder, position: Int) {
        holder.bind(_data[position])

    }

    override fun getItemCount(): Int {
        return _data.size

    }

    inner class BestMomentViewHolder(private val binding: ItemMomentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Pair<ResBestMoment.Data.TheBestMoment, ResBestMoment.Data.TheBestMoment>) {
            //binding.dvBestMoment.setWhereBookView(IS_RAINBOW)
            //binding.dvBestMoment.setLeftRainbow(data.first)


           // data.first.diaries[1].chapter
            //data.second
            //binding.dvBestMoment.setWhereBookView(IS_RAINBOW)
            //binding.dvBestMoment.setLeftRainbow(rainbowInfoData)
            //binding.dvBestMoment.setRightRainbow(rainbowInfoData)

        }


    }

    companion object {
        const val IS_RAINBOW = false
    }


}