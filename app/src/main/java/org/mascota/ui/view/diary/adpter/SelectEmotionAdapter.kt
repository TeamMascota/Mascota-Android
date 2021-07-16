package org.mascota.ui.view.diary.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference.getPetId
import org.mascota.data.remote.model.response.diary.ResPetInfo
import org.mascota.databinding.ItemEmotionBinding
import org.mascota.ui.view.calendar.CalendarFragment.Companion.ANIMAL_ANGRY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.ANIMAL_BORING
import org.mascota.ui.view.calendar.CalendarFragment.Companion.ANIMAL_JOY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.ANIMAL_LOVE
import org.mascota.ui.view.calendar.CalendarFragment.Companion.ANIMAL_SAD
import org.mascota.ui.view.calendar.CalendarFragment.Companion.ANIMAL_USUAL
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment.Companion.CAT
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment.Companion.DOG

class SelectEmotionAdapter : RecyclerView.Adapter<SelectEmotionAdapter.SelectEmotionViewHolder>() {
    private val _data = mutableListOf<ResPetInfo.Data.Pet>()
    var data: List<ResPetInfo.Data.Pet> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private var viewType = -1

    fun setItemViewType(type: Int) {
        viewType = type
        notifyDataSetChanged()
    }

    private val selectedList = MutableList(4) { false }

    private var deleteButtonClickListener: ((String, Int) -> Unit)? = null

    private var emoButtonClickListener: ((String, Int, Int) -> Unit)? = null

    fun setDeleteButtonClickListener(listener: (String, Int) -> Unit) {
        this.deleteButtonClickListener = listener
    }

    fun setEmoButtonClickListener(listener: (String, Int, Int) -> Unit) {
        this.emoButtonClickListener = listener
    }

    fun setSelectedList(index: Int, isShow: Boolean) {
        selectedList[index] = isShow
    }

    inner class SelectEmotionViewHolder(
        private val viewBiding: ItemEmotionBinding

    ) : RecyclerView.ViewHolder(
        viewBiding.root
    ) {
        fun onBind(data: ResPetInfo.Data.Pet, position: Int) {
            viewBiding.tvPetName.text = data.name

            with(viewBiding) {
                when(data.kind) {
                    CAT -> {
                        ivLove.setImageResource(R.drawable.selector_emo_cat_angry)
                        ivJoy.setImageResource(R.drawable.selector_emo_cat_joy)
                        ivUsual.setImageResource(R.drawable.selector_emo_cat_usual)
                        ivSad.setImageResource(R.drawable.selector_emo_cat_sad)
                        ivAngry.setImageResource(R.drawable.selector_emo_cat_angry)
                        ivBoring.setImageResource(R.drawable.selector_emo_cat_boring)
                    }
                    DOG -> {
                        ivLove.setImageResource(R.drawable.selector_emodog_love)
                        ivJoy.setImageResource(R.drawable.selector_emodog_joy)
                        ivUsual.setImageResource(R.drawable.selector_emodog_usual)
                        ivSad.setImageResource(R.drawable.selector_emodog_sad)
                        ivAngry.setImageResource(R.drawable.selector_emodog_angry)
                        ivBoring.setImageResource(R.drawable.seletor_emodog_boring)
                    }
                }

                isSelected = selectedList[position]

                if (!selectedList[position]) {
                    initReleaseEvent(viewBiding, clContainer)
                }

                ivAngry.setOnClickListener {
                    initReleaseEvent(viewBiding, ivAngry)
                    emoButtonClickListener?.invoke(getPetId(), position, ANIMAL_ANGRY)
                }
                ivBoring.setOnClickListener {
                    initReleaseEvent(viewBiding, ivBoring)
                    emoButtonClickListener?.invoke(getPetId(), position, ANIMAL_BORING)
                }
                ivJoy.setOnClickListener {
                    initReleaseEvent(viewBiding, ivJoy)
                    emoButtonClickListener?.invoke(getPetId(), position, ANIMAL_JOY)
                }
                ivLove.setOnClickListener {
                    initReleaseEvent(viewBiding, ivLove)
                    emoButtonClickListener?.invoke(getPetId(), position, ANIMAL_LOVE)
                }
                ivSad.setOnClickListener {
                    initReleaseEvent(viewBiding, ivSad)
                    emoButtonClickListener?.invoke(getPetId(), position, ANIMAL_SAD)
                }
                ivUsual.setOnClickListener {
                    initReleaseEvent(viewBiding, ivUsual)
                    emoButtonClickListener?.invoke(getPetId(), position, ANIMAL_USUAL)
                }
                btnDelete.setOnClickListener {
                    deleteButtonClickListener?.invoke(getPetId(), position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectEmotionViewHolder {
        val binding: ItemEmotionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_emotion, parent, false
        )

        return SelectEmotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectEmotionViewHolder, position: Int) {
        if(!selectedList[position]) {
            holder.itemView.visibility = View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        else {
            holder.itemView.visibility = View.VISIBLE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        holder.onBind(_data[position], position)
    }

    private fun initReleaseEvent(viewBinding: ItemEmotionBinding, view: View) {
        viewBinding.apply {
            (view == ivAngry).apply {
                tvAngry.isSelected = this
                ivAngry.isSelected = this
            }
            (view == ivSad).apply {
                tvSad.isSelected = this
                ivSad.isSelected = this
            }
            (view == ivJoy).apply {
                tvJoy.isSelected = this
                ivJoy.isSelected = this
            }
            (view == ivBoring).apply {
                tvBoring.isSelected = this
                ivBoring.isSelected = this
            }
            (view == ivUsual).apply {
                tvUsual.isSelected = this
                ivUsual.isSelected = this
            }
            (view == ivLove).apply {
                tvLove.isSelected = this
                ivLove.isSelected = this
            }
        }
    }

    override fun getItemCount(): Int = _data.size
}