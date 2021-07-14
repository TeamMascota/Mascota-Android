package org.mascota.ui.view.custom.book

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import org.mascota.R
import org.mascota.R.styleable.CustomBookView
import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.data.remote.model.response.rainbow.ResRainbowHome
import org.mascota.databinding.ViewCustomBookBinding
import org.mascota.ui.view.home.data.model.HomeDiaryInfoData
import org.mascota.util.CalendarUtil.convertCalendarToBeFamilyDateString
import org.mascota.util.CalendarUtil.convertCalendarToStoryDateString
import org.mascota.util.CalendarUtil.convertStringToCalendar
import org.mascota.util.StringUtil.makeChapterText
import org.mascota.util.StringUtil.makeEpisodeText
import org.mascota.util.extension.px

class BookView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var viewCustomBookBinding: ViewCustomBookBinding

    private lateinit var bgBookView: View
    private lateinit var topBookView: View
    private lateinit var lineView: View
    private lateinit var bottomLineView: View

    init {
        addView(createCustomView())
        setLeftDiaryFlag(false)
        initBindingView()
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val attrsCustomBookView = context.obtainStyledAttributes(attrs, CustomBookView)
        setAttr(attrsCustomBookView)
    }

    private fun initBindingView() {
        viewCustomBookBinding.apply {
            bgBookView = bgBook
            topBookView = topBook
            lineView = midLine
            bottomLineView = midBottomLine
        }
    }


    fun setLeftPart1Diary(diaryPart1Info: ResHomePart1.Data.FirstPartMainPage.Diary) {
        diaryPart1Info.apply {
            var chapterDiary: String = R.string.prolog.toString()
            if (chapter != 0) {
                chapterDiary = makeChapterText(chapter) + " " + makeEpisodeText(episode)
            }
            viewCustomBookBinding.layoutLeftPage.homeDiaryInfoData = HomeDiaryInfoData(
                chapterDiary, title, contents, date
            )
        }
    }

    /*fun setLeftBestMoment(bestMoment: ResBestMoment.Data.TheBestMoment.Diary){
        bestMoment.apply {
            viewCustomBookBinding.layoutLeftPage.apply {
                
            }



            }

            /*bestMoment = BestMomentData(
                BestMomentData.TheBestMoment.Diary(
                    chapter, episode, title, contents, date)
                )
        }*/

        }*/
    fun setLeftRainbow(rainbowDiaryInfoData: ResRainbowHome.Data.RainbowMainPage.Memory) {
        viewCustomBookBinding.layoutRainbowLeftPage.apply {
            rainbowDiaryInfo = rainbowDiaryInfoData
            val calendar = convertStringToCalendar(rainbowDiaryInfoData.date)
            tvStory.text = convertCalendarToStoryDateString(calendar)
            tvDate.text = convertCalendarToBeFamilyDateString(calendar)
            clDiary.visibility = View.VISIBLE
            ivLogo.visibility = View.GONE
        }
    }

    fun setRightRainbow(rainbowDiaryInfoData: ResRainbowHome.Data.RainbowMainPage.Memory) {
        viewCustomBookBinding.layoutRainbowRightPage.apply {
            rainbowDiaryInfo = rainbowDiaryInfoData
            clDiary.visibility = View.VISIBLE
            ivLogo.visibility = View.GONE
        }
    }

    fun setWhereBookView(isHome: Boolean) {
        viewCustomBookBinding.isHome = isHome
    }

    private fun setLeftDiaryFlag(isProlog: Boolean) {
        viewCustomBookBinding.layoutLeftPage.isProlog = isProlog
    }

    private fun setAttr(attrs: TypedArray) {
        attrs.apply {
            val lineColor = getColor(R.styleable.CustomBookView_lineColor, 0)
            lineView.setBackgroundColor(lineColor)
            bottomLineView.setBackgroundColor(lineColor)

            val bookColor = getColor(R.styleable.CustomBookView_bookStrokeColor, 0)
            (bgBookView.background as GradientDrawable).setStroke(px(1), bookColor)
            (topBookView.background as GradientDrawable).setStroke(px(1), bookColor)
        }
    }

    private fun createCustomView(): View {
        val inflater = LayoutInflater.from(context)
        viewCustomBookBinding =
            DataBindingUtil.inflate(inflater, R.layout.view_custom_book, this, false)

        return viewCustomBookBinding.root
    }
}