package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.WorldRegionsClassEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorldRegionsClassRelationalModel(
    @SerialName("americas")
    override val americas: AmericasRelationalModel = AmericasRelationalModel(),

    @SerialName("asiaPacific")
    override val asiaPacific: AsiaPacificRelationalModel = AsiaPacificRelationalModel(),

    @SerialName("europe")
    override val europe: EuropeRelationalModel = EuropeRelationalModel(),

    @SerialName("middleEastAfrica")
    override val middleEastAfrica: MiddleEastAfricaRelationalModel = MiddleEastAfricaRelationalModel()
) : WorldRegionsClassEntity
