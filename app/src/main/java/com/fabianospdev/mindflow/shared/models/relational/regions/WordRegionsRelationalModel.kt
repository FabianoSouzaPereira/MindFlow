package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.WordRegionsEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordRegionsRelationalModel(
    @SerialName("worldRegions")
    override val worldRegions: WorldRegionsClassRelationalModel
) : WordRegionsEntity








