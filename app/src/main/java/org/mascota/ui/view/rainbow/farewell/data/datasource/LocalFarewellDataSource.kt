package org.mascota.ui.view.rainbow.farewell.data.datasource

import org.mascota.ui.view.rainbow.farewell.data.model.PetInfoData

class LocalFarewellDataSource : FarewellDataSource {
    override fun getPetInfoData(): PetInfoData = PetInfoData("가나다라마바", 1, 23500, 5160, "최소은", "모여봐요 코봉의 숲")
}