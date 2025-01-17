package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.MiddleEastAfricaEntity

data class MiddleEastAfricaFirebaseModel(
    override val middleEast: List<CountryFirebaseModel>,
    override val northAfrica: List<CountryFirebaseModel>,
    override val southernAfrica: List<CountryFirebaseModel>
) : MiddleEastAfricaEntity
