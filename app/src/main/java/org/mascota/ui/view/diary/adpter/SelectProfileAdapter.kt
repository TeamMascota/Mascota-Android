package org.mascota.ui.view.diary.adpter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.mascota.R
import org.mascota.data.remote.model.response.diary.ResPetInfo
import org.mascota.data.remote.model.response.rainbow.ResFarewellSelect
import org.mascota.databinding.ItemProfileBinding
import org.mascota.ui.view.content.detail.adapter.ContentDetailDiaryAdapter
import org.mascota.ui.view.diary.data.ProfileList
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment.Companion.CAT
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment.Companion.DOG
import org.mascota.util.dp

class SelectProfileAdapter : RecyclerView.Adapter<SelectProfileAdapter.SelectProfileViewHolder>() {
    private val _data = mutableListOf<ResPetInfo.Data.Pet>()
    var data: List<ResPetInfo.Data.Pet> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private val selectedList = MutableList(4) { false }

    private var itemClickListener: ((String, Int) -> Unit)? = null
    private var itemShowListener: ((Int) -> Unit)? = null

    fun setSelectedList(index: Int, isShow: Boolean) {
        selectedList[index] = isShow
    }

    fun setItemClickListener(listener : ((String, Int) -> Unit)) {
        itemClickListener = listener
    }

    fun setItemShowListener(listener : ((Int) -> Unit)) {
        itemShowListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectProfileViewHolder {
        val binding: ItemProfileBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_profile, parent, false
        )

        return SelectProfileViewHolder(binding)
    }


    inner class SelectProfileViewHolder(
        private val viewBiding: ItemProfileBinding
    ) : RecyclerView.ViewHolder(
        viewBiding.root
    ) {
        fun onBind(data: ResPetInfo.Data.Pet, position: Int) {
            viewBiding.apply {
                petData = data
                Glide.with(ivProfile.context).load(data.img).transform(
                    CenterCrop(),
                    RoundedCorners(ContentDetailDiaryAdapter.IMAGE_RADIUS.dp)
                ).into(ivProfile)

                clItem.isSelected = selectedList[position]

                viewBiding.clItem.setOnClickListener {
                    if(!clItem.isSelected) {
                        clItem.isSelected = true
                        selectedList[position] = true
                        itemShowListener?.invoke(position)
                    }
                    else {
                        itemClickListener?.invoke(tvName.text.toString(), position)
                    }
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onBindViewHolder(holder: SelectProfileViewHolder, position: Int) {
        holder.onBind(data[position], position)
    }

    override fun getItemCount(): Int = _data.size

}

