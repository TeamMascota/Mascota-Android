package org.mascota.ui.view.content.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.mascota.R
import org.mascota.data.remote.model.response.content.ResContentDetail
import org.mascota.databinding.ItemDiaryMonthBinding
import org.mascota.ui.view.content.detail.SwipeHelperCallback
import org.mascota.util.StringUtil.makeMonthText
import org.mascota.util.StringUtil.makeTotalText
import org.mascota.util.dp
import org.mascota.util.extension.setTextPartialColor

class ContentDetailMonthAdapter :
    RecyclerView.Adapter<ContentDetailMonthAdapter.ContentDetailMonthViewHolder>() {

    private val _contentMonthList = mutableListOf<ResContentDetail.Data.PetChapterDiary.Monthly>()

    private var navigateDiaryReadListener: ((String) -> Unit)? = null

    fun setNavigateDiaryReadListener(listener: (String) -> Unit) {
        navigateDiaryReadListener = listener
    }

    var contentMonthList: List<ResContentDetail.Data.PetChapterDiary.Monthly> = _contentMonthList
        set(value) {
            _contentMonthList.clear()
            _contentMonthList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentDetailMonthViewHolder {
        val binding = ItemDiaryMonthBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentDetailMonthViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentDetailMonthViewHolder, position: Int) {
        holder.onBind(_contentMonthList[position])


    }

    override fun getItemCount(): Int = contentMonthList.size

    inner class ContentDetailMonthViewHolder(
        val binding: ItemDiaryMonthBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ClickableViewAccessibility")
        fun onBind(monthly: ResContentDetail.Data.PetChapterDiary.Monthly) {
            binding.tvMonth.text = makeMonthText(monthly.month)
            binding.tvTotal.text = binding.tvTotal.setTextPartialColor(
                R.color.maco_orange,
                2,
                2 + monthly.episodePerMonthCount.toString().length,
                makeTotalText(monthly.episodePerMonthCount)
            )
            ContentDetailDiaryAdapter().apply {
                contentDiaryList = monthly.diaries
                binding.rvContentDiary.adapter = this
                val swipeHelperCallback = SwipeHelperCallback()
                swipeHelperCallback.apply {
                    setClamp(SWIPE_FIX_WIDTH.dp.toFloat())
                }

                val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
                itemTouchHelper.attachToRecyclerView(binding.rvContentDiary)
                binding.rvContentDiary.apply {
                    setOnTouchListener { _, _ ->
                        swipeHelperCallback.removePreviousClamp(this)
                        false
                    }
                }

                setDiaryClickListener {
                    navigateDiaryReadListener?.invoke(it)
                }

            }
        }
    }

    companion object {
        const val SWIPE_FIX_WIDTH = 144
    }
}

