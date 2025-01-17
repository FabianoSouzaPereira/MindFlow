package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.CountryEntity

data class CountryFirebaseModel(
    override val idCountry: String,
    override val name: String,
    override val language: String,
    override val currency: String,
    override val timezone: String
) : CountryEntity