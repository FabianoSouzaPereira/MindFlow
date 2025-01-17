package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.MiddleEastAfricaEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MiddleEastAfricaRelationalModel(
    @SerialName("MiddleEast")
    override val middleEast: List<CountryRelationalModel>,

    @SerialName("NorthAfrica")
    override val northAfrica: List<CountryRelationalModel>,

    @SerialName("SouthernAfrica")
    override val southernAfrica: List<CountryRelationalModel>
) : MiddleEastAfricaEntity
