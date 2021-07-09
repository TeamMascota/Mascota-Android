package org.mascota.ui.view.rainbow.farewell.data.datasource

import org.mascota.ui.view.rainbow.farewell.data.model.PetInfoData

interface FarewellDataSource {
    fun getPetInfoData() : PetInfoData
}