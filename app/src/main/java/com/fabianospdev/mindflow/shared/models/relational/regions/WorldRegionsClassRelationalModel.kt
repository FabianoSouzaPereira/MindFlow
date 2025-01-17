package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.WorldRegionsClassEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorldRegionsClassRelationalModel(
    @SerialName("americas")
    override val americas: AmericasRelationalModel,

    @SerialName("asiaPacific")
    override val asiaPacific: AsiaPacificRelationalModel,

    @SerialName("europe")
    override val europe: EuropeRelationalModel,

    @SerialName("middleEastAfrica")
    override val middleEastAfrica: MiddleEastAfricaRelationalModel
) : WorldRegionsClassEntity