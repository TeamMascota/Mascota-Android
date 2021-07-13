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

    fun makeTotalText(episode: Int) : String = "총 ${episode}화"

    fun makeAuthorText(text : String) : String = "작가 $text"

    fun makeWithStoryText(episode: String) : String = "작가님과 함께했던 ${episode}의 이야기속에서"

    fun makePetInfoText(name : String, species : String) : String = "${name}(은)는 의젓하고 당당한 ${species}로서"

    fun makeBestMomentText(name : String) : String = "${name}(이)가 느꼈던 최고의 순간들을"

    fun makPetNameText(name: String) : String = name

    fun makeNowEpisode(text : String) : String = "지금까지 쓴 책은 $text"

    fun makeNowDay(text : String) : String = "함께 보낸 날은 $text"

    fun makeTogetherDay(day: Int) : String = "함께한 지 ${day}일"

    fun makePetLifeText(text : String) : String = "${text}의 행복한 시간들을 잘 보셨나요?\n작가님과 함께했던 ${text}의 삶이 어땠는지"

    fun makeDenyText(pet : String) : String = "${pet}(이)와 이별했다고 해서 함께 한 추억까지 사라지는 것은 아닙니다. 작가님과 ${pet}(이)의 추억은 시간이 지나도 영원히 ${pet}(이)와 작가님의 기억 속에 살아 숨쉬고 있을 거예요."

    fun makeRegretText(pet : String) : String = "${pet}(이)의 짧고도 긴 삶에서 ${pet}(이)는 작가님 덕에 새로운 모험을 많이 했습니다. 새로운 사료도 먹어보고, 맛있는 간식도 먹어봤고, 때로는 재미있는 놀이로 ${pet}(이)를 기쁘게 해줬습니다. 그러니 후회하지 마세요. 작가님은 최선을 다해 ${pet}(이)를 행복하게 했습니다."

    fun makeLossText(pet : String) : String = "상실감은 이별 후 느끼는 자연스러운 감정입니다. 사랑하는 이와 이별할 때 상실감을 느끼지 않는 사람은 없습니다. 작가님이 나약하기 때문이 아니며, 작가님과 ${pet}의 관계가 깊었기 때문에 느끼는 당연한 감정입니다."

    fun makeAcceptText(pet : String) : String = "이별을 받아들이기까지 많은 감정이 오고갔지만, 작가님은 결국 모든 걸 극복하고 수용했습니다. ${pet}(이)도 작가님의 이런 모습을 보고 행복해하고 있을 거예요."

    fun setTextPartialBold(start : Int, end : Int, text : String) : SpannableString {
        val resultString = SpannableString(text)

        resultString.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return resultString
    }

    fun makeChapterText(chapter: Int) : String = "제 ${chapter}장"
}