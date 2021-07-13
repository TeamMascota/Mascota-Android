package org.mascota.ui.view.rainbow.adapter

import android.text.SpannableString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.data.remote.model.response.rainbow.ResRainbowHome
import org.mascota.databinding.ItemHelpMessageBinding
import org.mascota.ui.view.rainbow.data.model.HelpInfoData
import org.mascota.util.StringUtil.makeOrganizationText

class HelpAdapter: RecyclerView.Adapter<HelpAdapter.HelpViewHolder>() {
    private var helpMessageClickListener: ((String) -> Unit) ?= null
    private var stringColorConverter: ((String, Int) -> SpannableString) ?= null
    private val _data = mutableListOf<ResRainbowHome.Data.RainbowMainPage.Help>()
    var data: List<ResRainbowHome.Data.RainbowMainPage.Help> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    fun setHelpMessageClickListener(listener : (String) -> Unit) {
        this.helpMessageClickListener = listener
    }

    fun setColorConverter(listener : (String, Int) -> SpannableString) {
        this.stringColorConverter = listener
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
        fun bind(helpInfoData : ResRainbowHome.Data.RainbowMainPage.Help) {
            binding.apply {
                val organization = makeOrganizationText(helpInfoData.classification)
                val spannableText = stringColorConverter?.invoke(organization + helpInfoData.title, organization.length)
                tvContent.text = spannableText
                clHelp.setOnClickListener { helpMessageClickListener?.invoke(helpInfoData.url) }
            }
        }
    }
}