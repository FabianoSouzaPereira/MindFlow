package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.EuropeEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EuropeRelationalModel(
    @SerialName("NorthernEurope")
    override val northernEurope: List<CountryRelationalModel>,

    @SerialName("SouthernEurope")
    override val southernEurope: List<CountryRelationalModel>,

    @SerialName("EasternEurope")
    override val easternEurope: List<CountryRelationalModel>,

    @SerialName("WesternEurope")
    override val westernEurope: List<CountryRelationalModel>
) : EuropeEntity