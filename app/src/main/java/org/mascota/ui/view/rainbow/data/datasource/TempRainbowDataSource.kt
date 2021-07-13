package org.mascota.ui.view.rainbow.data.datasource

import org.mascota.ui.view.rainbow.data.model.HelpInfoData
import org.mascota.ui.view.rainbow.data.model.RainbowInfoData

interface TempRainbowDataSource {
    fun getHelpInfoData(): List<HelpInfoData>
    fun getRainbowInfoData(): RainbowInfoData
}