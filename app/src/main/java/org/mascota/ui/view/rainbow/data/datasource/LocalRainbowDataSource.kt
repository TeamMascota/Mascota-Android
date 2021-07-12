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

    override fun getLoveBestMomentData(): RainbowInfoData = RainbowInfoData(
        listOf(
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "과제충 판정 ㅠㅠ",
                "쬐끄매 보이는 게 어떻게 8.7키로나 되는 거지 믿을 수가 없다. 중성화 이후로 많이 먹은 탓...",
                4
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "사랑2",
                "사랑사랑사랑사랑사랑사랑사랑사랑사랑사랑222222222222",
                4
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "사랑3",
                "사랑사랑사랑사랑사랑사랑사랑사랑사랑사랑33333333333",
                4
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "사랑4",
                "사랑사랑사랑사랑사랑사랑사랑사랑사랑사랑444444444444",
                4
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "사랑5",
                "사랑사랑사랑사랑사랑사랑사랑사랑사랑사랑555555555555",
                4
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "사랑6",
                "사랑사랑사랑사랑사랑사랑사랑사랑사랑사랑6666666666666",
                4
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "사랑7",
                "사랑사랑사랑사랑사랑사랑사랑사랑사랑사랑777777777777",
                4
            ),

            )

    )

    override fun getJoyBestMomentData(): RainbowInfoData= RainbowInfoData(
        listOf(
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "기쁨1",
                "기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨111111",
                3
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "기쁨2",
                "기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨22222",
                3
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "기쁨3",
                "기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨333333",
                3
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "기쁨4",
                "기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨44444",
                3
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "기쁨5",
                "기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨555555",
                3
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "기쁨6",
                "기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨666666",
                3
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "기쁨7",
                "기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨7777777",
                3
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "기쁨8",
                "기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨기쁨888888",
                3
            ),


            )


    )

    override fun getAngryBestMomentData(): RainbowInfoData = RainbowInfoData(
        listOf(
            RainbowInfoData.Data(
            Calendar.getInstance(),
            "화남1",
            "화남화남화남화남화남화남화남화남화남1111",
            1
        ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "화남2",
                "화남화남화남화남화남화남화남화남화남222222",
                1
            ),
        )
            )

    override fun getUsualMomentData(): RainbowInfoData = RainbowInfoData(
        listOf(
            RainbowInfoData.Data(
            Calendar.getInstance(),
            "보통1",
            "보통보통보통보통보통보통보통11111111",
            5
        ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "보통2",
                "보통보통보통보통보통보통보통222222",
                5
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "보통3",
                "보통보통보통보통보통보통보통3333333",
                5
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "보통4",
                "보통보통보통보통보통보통보통4444444",
                5
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "보통5",
                "보통보통보통보통보통보통보통555555",
                5
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "보통6",
                "보통보통보통보통보통보통보통6666666",
                5
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "보통7",
                "보통보통보통보통보통보통보통777777",
                5
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "보통8",
                "보통보통보통보통보통보통보통보통888888",
                5
            ),
            )

    )

    override fun getSadMomentData(): RainbowInfoData= RainbowInfoData (
        listOf(
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "우울1",
                "우울우울우울우울우울우울111111",
                0
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "우울2",
                "우울우울우울우울우울우울22222222",
                0
            ),
        )
            )

    override fun getBoringMomentData(): RainbowInfoData = RainbowInfoData(
        listOf(
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "심심1",
                "심심심심심심심심심심심심11111111",
                2
            ),
            RainbowInfoData.Data(
                Calendar.getInstance(),
                "심심2",
                "심심심심심심심심심심심심22222222",
                2
            ),
        )

    )

}