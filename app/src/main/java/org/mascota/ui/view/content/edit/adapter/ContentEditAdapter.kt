package org.mascota.ui.view.content.edit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mascota.data.remote.model.response.content.ResContentList
import org.mascota.databinding.ItemContentEditBinding
import org.mascota.ui.view.content.edit.data.model.ContentEditInfoData
import org.mascota.util.StringUtil.makeChapterText

class ContentEditAdapter : RecyclerView.Adapter<ContentEditAdapter.ContentEditViewHolder>() {

    private var deleteClickListener: ((String, String) -> Unit)? = null

    fun setDeleteClickListener(listener: (String, String) -> Unit) {
        deleteClickListener = listener
    }

    private var editClickLister: ((String, String, String) -> Unit)? = null

    fun setEditClickListener(listener: (String, String, String) -> Unit) {
        editClickLister = listener
    }

    private val _contentEditList = mutableListOf<ResContentList.TableContent>()

    var contentEditList: List<ResContentList.TableContent> = _contentEditList
        set(value) {
            _contentEditList.clear()
            _contentEditList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentEditViewHolder {
        val binding = ItemContentEditBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentEditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentEditViewHolder, position: Int) {
        holder.onBind(_contentEditList[position])
    }

    override fun getItemCount(): Int = contentEditList.size

    inner class ContentEditViewHolder(
        private val binding: ItemContentEditBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(content: ResContentList.TableContent) {
            content.apply {
                binding.contentEditInfoData = ContentEditInfoData(chapter, chapterTitle)
                binding.tvDelete.setOnClickListener {
                    deleteClickListener?.invoke(makeChapterText(chapter) + " " + chapterTitle, chapterId)
                }
                binding.tvEdit.setOnClickListener {
                    editClickLister?.invoke(
                        makeChapterText(chapter),
                        chapterTitle,
                        chapterId
                    )
                }
            }
        }
    }
}