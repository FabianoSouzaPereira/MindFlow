package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.AsiaPacificEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AsiaPacificRelationalModel(
    @SerialName("CentralAsia")
    override val centralAsia: List<CountryRelationalModel>,

    @SerialName("SouthAsia")
    override val southAsia: List<CountryRelationalModel>,

    @SerialName("NortheastAsia")
    override val northeastAsia: List<CountryRelationalModel>,

    @SerialName("SoutheastAsia")
    override val southeastAsia: List<CountryRelationalModel>,

    @SerialName("Australia")
    override val australia: List<CountryRelationalModel>,

    @SerialName("Oceania")
    override val oceania: List<CountryRelationalModel>
) : AsiaPacificEntity