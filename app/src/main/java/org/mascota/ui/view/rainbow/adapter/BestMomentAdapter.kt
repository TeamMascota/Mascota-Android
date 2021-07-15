package org.mascota.ui.view.rainbow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.data.remote.model.response.rainbow.ResBestMoment
import org.mascota.data.remote.model.response.rainbow.ResRainbowHome
import org.mascota.databinding.ItemMomentBinding
import org.mascota.ui.view.rainbow.farewell.data.model.BestMomentInfoData

class BestMomentAdapter : RecyclerView.Adapter<BestMomentAdapter.BestMomentViewHolder>() {

    lateinit var emo : Pair<Int, Int>

    private val _data = mutableListOf<Pair<ResBestMoment.Data.TheBestMoment.Diary, ResBestMoment.Data.TheBestMoment.Diary>>()
    var data: List<Pair<ResBestMoment.Data.TheBestMoment.Diary, ResBestMoment.Data.TheBestMoment.Diary>> = _data
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
        fun bind(data : Pair<ResBestMoment.Data.TheBestMoment.Diary, ResBestMoment.Data.TheBestMoment.Diary>) {
            data.first.apply {
                binding.dvBestMoment.setLeftBestMoment(BestMomentInfoData(
                    chapter, episode, title, contents, date, emo.first, emo.second
                ))
            }
            data.second.apply {
                binding.dvBestMoment.setRightBestMoment(BestMomentInfoData(
                    chapter, episode, title, contents, date, emo.first, emo.second
                ))
            }
        }
    }

    companion object {
        const val IS_RAINBOW = false
    }


}