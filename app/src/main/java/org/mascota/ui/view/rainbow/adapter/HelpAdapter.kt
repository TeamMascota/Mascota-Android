package org.mascota.ui.view.rainbow.adapter

import android.text.SpannableString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.databinding.ItemHelpMessageBinding
import org.mascota.ui.view.rainbow.data.model.HelpInfoData
import org.mascota.util.StringUtil.makeOrganizationText

class HelpAdapter: RecyclerView.Adapter<HelpAdapter.HelpViewHolder>() {
    private var helpMessageClickListener: ((String) -> Unit) ?= null
    private var stringColorConverter: ((String, Int) -> SpannableString) ?= null
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
        fun bind(helpInfoData : HelpInfoData) {
            binding.apply {
                val organization = makeOrganizationText(helpInfoData.organization)
                val spannableText = stringColorConverter?.invoke(organization + helpInfoData.content, organization.length)
                tvContent.text = spannableText
                clHelp.setOnClickListener { helpMessageClickListener?.invoke(helpInfoData.url) }
            }
        }
    }
}