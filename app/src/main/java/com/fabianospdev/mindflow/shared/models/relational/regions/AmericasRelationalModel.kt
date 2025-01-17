package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.AmericasEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmericasRelationalModel(
    @SerialName("northAmerica")
    override val northAmerica: List<CountryRelationalModel>,

    @SerialName("southAmerica")
    override val southAmerica: List<CountryRelationalModel>,

    @SerialName("centralAmerica")
    override val centralAmerica: List<CountryRelationalModel>,

    @SerialName("caribbean")
    override val caribbean: List<CountryRelationalModel>
) : AmericasEntity