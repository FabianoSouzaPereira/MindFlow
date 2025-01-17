package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.AsiaPacificEntity

data class AsiaPacificFirebaseModel(
    override val centralAsia: List<CountryFirebaseModel>,
    override val southAsia: List<CountryFirebaseModel>,
    override val northeastAsia: List<CountryFirebaseModel>,
    override val southeastAsia: List<CountryFirebaseModel>,
    override val australia: List<CountryFirebaseModel>,
    override val oceania: List<CountryFirebaseModel>
) : AsiaPacificEntity