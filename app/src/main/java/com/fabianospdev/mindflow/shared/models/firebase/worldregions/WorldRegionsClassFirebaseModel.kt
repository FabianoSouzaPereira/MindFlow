package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.WorldRegionsClassEntity

data class WorldRegionsClassFirebaseModel(
    override val americas: AmericasFirebaseModel = AmericasFirebaseModel(),
    override val asiaPacific: AsiaPacificFirebaseModel = AsiaPacificFirebaseModel(),
    override val europe: EuropeFirebaseModel = EuropeFirebaseModel(),
    override val middleEastAfrica: MiddleEastAfricaFirebaseModel = MiddleEastAfricaFirebaseModel()
) : WorldRegionsClassEntity