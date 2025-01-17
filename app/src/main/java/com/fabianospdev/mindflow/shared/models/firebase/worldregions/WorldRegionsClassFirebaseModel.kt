package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.WorldRegionsClassEntity

data class WorldRegionsClassFirebaseModel(
    override val americas: AmericasFirebaseModel,
    override val asiaPacific: AsiaPacificFirebaseModel,
    override val europe: EuropeFirebaseModel,
    override val middleEastAfrica: MiddleEastAfricaFirebaseModel
) : WorldRegionsClassEntity