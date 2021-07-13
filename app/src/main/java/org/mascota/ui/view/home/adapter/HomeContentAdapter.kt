package org.mascota.ui.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.databinding.ItemContentBinding
import org.mascota.util.StringUtil.makePartText
import org.mascota.util.StringUtil.makeTotalText

class HomeContentAdapter : RecyclerView.Adapter<HomeContentAdapter.HomeContentViewHolder>() {

    private val _contentList = mutableListOf<ResHomePart1.Data.FirstPartMainPage.TableContent>()

    private var navigateContentDetail: (() -> Unit)? = null

    fun setNavigateContentDetail(setter: () -> Unit) {
        navigateContentDetail = setter
    }

    var contentList: List<ResHomePart1.Data.FirstPartMainPage.TableContent> = _contentList
        set(value) {
            _contentList.clear()
            _contentList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeContentViewHolder {
        val binding = ItemContentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeContentViewHolder, position: Int) {
        holder.onBind(contentList[position])
    }

    override fun getItemCount(): Int = contentList.size

    inner class HomeContentViewHolder(
        private val binding: ItemContentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(tableContent: ResHomePart1.Data.FirstPartMainPage.TableContent) {
            binding.apply {
                with(tableContent) {
                    tvChapter.text = makePartText(chapter)
                    tvTitle.text = chapterName
                    tvEpisode.text = makeTotalText(episodePerchapterCount)
                }
            }
            binding.clItemContent.setOnClickListener {
                navigateContentDetail?.invoke()
            }
        }
    }
}