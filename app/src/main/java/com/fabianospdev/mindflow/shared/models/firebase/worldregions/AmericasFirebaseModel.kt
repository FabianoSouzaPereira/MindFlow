package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.AmericasEntity

data class AmericasFirebaseModel(
    override val northAmerica: List<CountryFirebaseModel>,
    override val southAmerica: List<CountryFirebaseModel>,
    override val centralAmerica: List<CountryFirebaseModel>,
    override val caribbean: List<CountryFirebaseModel>
) : AmericasEntity