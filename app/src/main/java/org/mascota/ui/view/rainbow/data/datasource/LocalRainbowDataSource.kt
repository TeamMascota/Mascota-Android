package org.mascota.ui.view.rainbow.data.datasource

import org.mascota.ui.view.rainbow.data.model.HelpInfoData
import org.mascota.ui.view.rainbow.data.model.RainbowInfoData
import java.util.*

class LocalRainbowDataSource : RainbowDataSource {
    override fun getHelpInfoData(): List<HelpInfoData> = listOf(
        HelpInfoData("펫카드", "무지개다리 건넌 강아지, 방치하지 마세요", "https://github.com/mdb1217"),
        HelpInfoData("펫카드", "무지개다리 건넌 강아지, 방치하지 마세요", "https://github.com/mdb1217"),
        HelpInfoData("펫카드", "무지개다리 건넌 강아지, 방치하지 마세요", "https://github.com/mdb1217"),
        HelpInfoData("펫카드", "무지개다리 건넌 강아지, 방치하지 마세요ㅇㅗㅏㄹㅏㄹㅏㄹㅏㄹㅏㄹㅏ", "https://github.com/mdb1217"),
        HelpInfoData("펫카드", "무지개다리 건넌 강아지, 방치하지 마세요ㅇㅗㅏㄹㅏㄹㅏㄹㅏㄹㅏㄹㅏ", "https://github.com/mdb1217"),
        HelpInfoData("펫카드", "무지개다리 건넌 강아지, 방치하지 마세요ㅇㅗㅏㄹㅏㄹㅏㄹㅏㄹㅏㄹㅏ", "https://github.com/mdb1217")
    )

    override fun getRainbowInfoData(): RainbowInfoData = RainbowInfoData(
        listOf(
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "과제충 판정 ㅠㅠ",
                "쬐끄매 보이는 게 어떻게 8.7키로나 되는 거지 믿을 수가 없다. 중성화 이후로 많이 먹은 탓...",
                3
            )
        )
    )
}