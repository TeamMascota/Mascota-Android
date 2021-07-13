package org.mascota.ui.view.diary.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.mascota.R
import org.mascota.databinding.ItemImageBinding
import org.mascota.ui.view.diary.data.ImageList

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    val imageList = mutableListOf<ImageList>()



    inner class ImageViewHolder(
        private val viewBinding : ItemImageBinding
    ): RecyclerView.ViewHolder(
        viewBinding.root
    ){

        fun onBind(data: ImageList, position: Int){
            viewBinding.image = data
            viewBinding.ivImg.setImageURI(data.img)
            viewBinding.ivEmpty.visibility = View.INVISIBLE

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding : ItemImageBinding = DataBindingUtil.inflate(

            LayoutInflater.from(parent.context),
            R.layout.item_image, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(imageList[position],position)

    }

    override fun getItemCount(): Int = imageList.size

}