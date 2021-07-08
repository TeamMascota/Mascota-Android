package org.mascota.util

object StringUtil {
    fun makeHeroNumbering(position: Int): String = "주인공 $position"

    fun makeHeroNumberingAndName(position: Int): String = "주인공 ${position}의 이름"

    fun makeTextLength(length : Int) : String = "($length"

    fun makeOrganizationText(text : String) : String = "[$text] "
}