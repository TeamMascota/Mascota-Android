package org.mascota.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.mascota.R
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_ANGRY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_BORING
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_JOY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_LOVE
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_SAD
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_USUAL
import org.mascota.ui.view.calendar.CalendarFragment.Companion.EMPTY
import org.mascota.ui.view.content.detail.adapter.ContentDetailDiaryAdapter
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment.Companion.CAT
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment.Companion.DOG
import org.mascota.util.CalendarUtil.convertCalendarToBeFamilyDateString
import org.mascota.util.CalendarUtil.convertCalendarToStoryDateString
import java.util.*

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imgEmo")
    fun setDogEmo(image: ImageView, emo: Int?) {
        with(image) {
            when (emo) {
                DOG_SAD -> {
                    setImageResource(R.drawable.ic_dog_sad)
                    visibility = View.VISIBLE
                }
                DOG_ANGRY -> {
                    setImageResource(R.drawable.ic_dog_angry)
                    visibility = View.VISIBLE
                }
                DOG_BORING -> {
                    setImageResource(R.drawable.ic_dog_boring)
                    visibility = View.VISIBLE
                }
                DOG_JOY -> {
                    setImageResource(R.drawable.ic_dog_joy)
                    visibility = View.VISIBLE
                }
                DOG_LOVE -> {
                    setImageResource(R.drawable.ic_dog_love)
                    visibility = View.VISIBLE
                }
                DOG_USUAL -> {
                    setImageResource(R.drawable.ic_dog_usual)
                    visibility = View.VISIBLE
                }
                EMPTY -> {
                    setImageResource(R.drawable.ic_empty)
                    visibility = View.VISIBLE
                }
                else -> visibility = View.INVISIBLE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("emo", "species")
    fun setEmo(image: ImageView, emo: Int?, species: Int) {
        with(image) {
            when (species) {
                DOG -> {
                    when (emo) {
                        DOG_SAD -> {
                            setImageResource(R.drawable.ic_dog_sad)
                            visibility = View.VISIBLE
                        }
                        DOG_ANGRY -> {
                            setImageResource(R.drawable.ic_dog_angry)
                            visibility = View.VISIBLE
                        }
                        DOG_BORING -> {
                            setImageResource(R.drawable.ic_dog_boring)
                            visibility = View.VISIBLE
                        }
                        DOG_JOY -> {
                            setImageResource(R.drawable.ic_dog_joy)
                            visibility = View.VISIBLE
                        }
                        DOG_LOVE -> {
                            setImageResource(R.drawable.ic_dog_love)
                            visibility = View.VISIBLE
                        }
                        DOG_USUAL -> {
                            setImageResource(R.drawable.ic_dog_usual)
                            visibility = View.VISIBLE
                        }
                        EMPTY -> {
                            setImageResource(R.drawable.ic_empty)
                            visibility = View.VISIBLE
                        }
                        else -> visibility = View.INVISIBLE
                    }
                }
                CAT -> {
                    when (emo) {
                        DOG_SAD -> {
                            setImageResource(R.drawable.ic_dog_sad)
                            visibility = View.VISIBLE
                        }
                        DOG_ANGRY -> {
                            setImageResource(R.drawable.ic_dog_angry)
                            visibility = View.VISIBLE
                        }
                        DOG_BORING -> {
                            setImageResource(R.drawable.ic_dog_boring)
                            visibility = View.VISIBLE
                        }
                        DOG_JOY -> {
                            setImageResource(R.drawable.ic_dog_joy)
                            visibility = View.VISIBLE
                        }
                        DOG_LOVE -> {
                            setImageResource(R.drawable.ic_dog_love)
                            visibility = View.VISIBLE
                        }
                        DOG_USUAL -> {
                            setImageResource(R.drawable.ic_dog_usual)
                            visibility = View.VISIBLE
                        }
                        EMPTY -> {
                            setImageResource(R.drawable.ic_empty)
                            visibility = View.VISIBLE
                        }
                        else -> visibility = View.INVISIBLE
                    }
                }
                else -> {
                    when (emo) {
                        DENY -> {
                            setImageResource(R.drawable.selector_human_deny)
                            visibility = View.VISIBLE
                        }
                        ANGRY -> {
                            setImageResource(R.drawable.selector_human_angry)
                            visibility = View.VISIBLE
                        }
                        REGRET -> {
                            setImageResource(R.drawable.selector_human_regret)
                            visibility = View.VISIBLE
                        }
                        LOSS -> {
                            setImageResource(R.drawable.selector_human_loss)
                            visibility = View.VISIBLE
                        }
                        SAD -> {
                            setImageResource(R.drawable.selector_human_sad)
                            visibility = View.VISIBLE
                        }
                        ACCEPT -> {
                            setImageResource(R.drawable.selector_human_accept)
                            visibility = View.VISIBLE
                        }
                        EMPTY -> {
                            setImageResource(R.drawable.ic_empty)
                            visibility = View.VISIBLE
                        }
                        else -> visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imgPersonEmo")
    fun setPersonEmo(image: ImageView, imgPersonEmo: Int?) {
        with(image) {
            when (imgPersonEmo) {
                SAD -> {
                    setImageResource(R.drawable.ic_human_sad_big)
                    visibility = View.VISIBLE
                }
                ANGRY -> {
                    setImageResource(R.drawable.ic_human_angry_big)
                    visibility = View.VISIBLE
                }
                DENY -> {
                    setImageResource(R.drawable.ic_human_deny_big)
                    visibility = View.VISIBLE
                }
                REGRET -> {
                    setImageResource(R.drawable.ic_human_regret_big)
                    visibility = View.VISIBLE
                }
                LOSS -> {
                    setImageResource(R.drawable.ic_human_loss_big)
                    visibility = View.VISIBLE
                }
                else -> {
                    setImageResource(R.drawable.ic_human_accept_big)
                    visibility = View.VISIBLE
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("isEmpty")
    fun setRecordSizeVisibility(textView: TextView, isEmpty: Boolean?) {
        with(textView) {
            visibility = when (isEmpty) {
                true -> View.GONE
                false -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imgUrl")
    fun setRemoteImage(image: ImageView, url: String?) {
        Glide.with(image.context).load(url).into(image)
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("titleText")
    fun setEpisodeText(textView: TextView, number: Int?) {
        textView.text = "${number}화 기록하기"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("chapterText")
    fun setChapterText(textView: TextView, number: Int?) {
        textView.text = "제 ${number}장"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("totalText")
    fun setTotalText(textView: TextView, number: Int?) {
        textView.text = "총 ${number}화"
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("monthText")
    fun setMonthText(textView: TextView, number: Int?) {
        textView.text = "${number}월"
    }

    @JvmStatic
    @BindingAdapter("yearMonthStory")
    fun setYearMonthStoryText(textView: TextView, calendar: Calendar?) {
        if (calendar != null)
            textView.text = convertCalendarToStoryDateString(calendar)
    }

    @JvmStatic
    @BindingAdapter("yearMonthDay")
    fun setYearMonthDayText(textView: TextView, calendar: Calendar?) {
        if (calendar != null)
            textView.text = convertCalendarToBeFamilyDateString(calendar)
    }

    @JvmStatic
    @BindingAdapter("remoteRoundedImg")
    fun setRemoteRoundedImg(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).transform(
            CenterCrop(),
            RoundedCorners(ContentDetailDiaryAdapter.IMAGE_RADIUS.dp)
        ).into(imageView)
    }

    const val PERSON = 2
    const val DENY = 0
    const val ANGRY = 1
    const val REGRET = 2
    const val LOSS = 3
    const val SAD = 4
    const val ACCEPT = 5
}