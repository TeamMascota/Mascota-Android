package org.mascota.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan

object StringUtil {
    fun makeHeroNumbering(position: Int): String = "주인공 $position"

    fun makeHeroNumberingAndName(position: Int): String = "주인공 ${position}의 이름"

    fun makeTextLength(length : Int) : String = "($length"

    fun makeOrganizationText(text : String) : String = "[$text] "

    fun makeEpisodeText(episode : Int) : String = "${episode}화"

    fun makeDayText(day : Int) : String = "${day}일"

    fun makeAllText(text : String) : String = "총 $text"

    fun makeAuthorText(text : String) : String = "작가 $text"

    fun makeWithStoryText(episode: String) : String = "작가님과 함께했던 ${episode}의 이야기속에서"

    fun makePetInfoText(name : String, species : String) : String = "${name}(은)는 의젓하고 당당한 ${species}로서"

    fun makeBestMomentText(name : String) : String = "${name}(이)가 느꼈던 최고의 순간들을"

    fun makPetNameText(name: String) : String = name

    fun makeNowEpisode(text : String) : String = "지금까지 쓴 책은 $text"

    fun makeNowDay(text : String) : String = "함께 보낸 날은 $text"

    fun makePetLifeText(text : String) : String = "${text}의 행복한 시간들을 잘 보셨나요?\n작가님과 함께했던 ${text}의 삶이 어땠는지"

    fun setTextPartialBold(start : Int, end : Int, text : String) : SpannableString {
        val resultString = SpannableString(text)

        resultString.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return resultString
    }
}