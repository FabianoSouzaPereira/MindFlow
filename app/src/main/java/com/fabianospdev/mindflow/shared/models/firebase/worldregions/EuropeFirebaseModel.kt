package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.EuropeEntity

data class EuropeFirebaseModel(
    override val northernEurope: List<CountryFirebaseModel>,
    override val southernEurope: List<CountryFirebaseModel>,
    override val easternEurope: List<CountryFirebaseModel>,
    override val westernEurope: List<CountryFirebaseModel>
) : EuropeEntity