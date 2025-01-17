package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.CountryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryRelationalModel(
    @SerialName("idCountry")
    override val idCountry: String,

    @SerialName("name")
    override val name: String,

    @SerialName("language")
    override val language: String,

    @SerialName("currency")
    override val currency: String,

    @SerialName("timezone")
    override val timezone: String
) : CountryEntity