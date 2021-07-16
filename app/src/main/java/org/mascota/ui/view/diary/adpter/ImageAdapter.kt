package org.mascota.ui.view.diary.adpter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.mascota.R
import org.mascota.databinding.ItemImageBinding
import org.mascota.ui.view.content.detail.adapter.ContentDetailDiaryAdapter
import org.mascota.ui.view.diary.data.ImageList
import org.mascota.util.dp

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private val _data = mutableListOf<Uri?>()
    var data: List<Uri?> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private val selectedList = MutableList(5) { false }

    fun setSelectedList(position:Int) {
        selectedList[position] = true
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(
        private val viewBinding : ItemImageBinding
    ): RecyclerView.ViewHolder(
        viewBinding.root
    ){
        fun onBind(data: Uri?, position: Int){
            viewBinding.clImg.isSelected = selectedList[position]
            Glide.with(viewBinding.ivImg).load(data).transform(
                CenterCrop(),
                RoundedCorners(ContentDetailDiaryAdapter.IMAGE_RADIUS.dp)
            ).into(viewBinding.ivImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding : ItemImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_image, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(_data[position],position)
    }

    override fun getItemCount(): Int = _data.size

}