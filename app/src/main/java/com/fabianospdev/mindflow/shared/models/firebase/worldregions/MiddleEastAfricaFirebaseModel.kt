package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.MiddleEastAfricaEntity

data class MiddleEastAfricaFirebaseModel(
    override val middleEast: List<CountryFirebaseModel> = defaultMiddleEast,
    override val northAfrica: List<CountryFirebaseModel> = defaultNorthAfrica,
    override val southernAfrica: List<CountryFirebaseModel> = defaultSouthernAfrica,
) : MiddleEastAfricaEntity {
    companion object {
        val defaultMiddleEast = listOf(
            CountryFirebaseModel("1", "Saudi Arabia", "Arabic", "SAR", "GMT+3"),
            CountryFirebaseModel("2", "Iran", "Persian", "IRR", "GMT+3:30"),
            CountryFirebaseModel("3", "Iraq", "Arabic/Kurdish", "IQD", "GMT+3"),
            CountryFirebaseModel("4", "Israel", "Hebrew", "ILS", "GMT+2"),
            CountryFirebaseModel("5", "Jordan", "Arabic", "JOD", "GMT+2")
        )

        val defaultNorthAfrica = listOf(
            CountryFirebaseModel("6", "Egypt", "Arabic", "EGP", "GMT+2"),
            CountryFirebaseModel("7", "Algeria", "Arabic/Berber", "DZD", "GMT+1"),
            CountryFirebaseModel("8", "Morocco", "Arabic/Berber", "MAD", "GMT+0"),
            CountryFirebaseModel("9", "Tunisia", "Arabic", "TND", "GMT+1"),
            CountryFirebaseModel("10", "Libya", "Arabic", "LYD", "GMT+2")
        )

        val defaultSouthernAfrica = listOf(
            CountryFirebaseModel("11", "South Africa", "English/Zulu/Xhosa", "ZAR", "GMT+2"),
            CountryFirebaseModel("12", "Botswana", "English/Setswana", "BWP", "GMT+2"),
            CountryFirebaseModel("13", "Namibia", "English/Afrikaans", "NAD", "GMT+2"),
            CountryFirebaseModel("14", "Zimbabwe", "English/Shona", "ZWL", "GMT+2"),
            CountryFirebaseModel("15", "Mozambique", "Portuguese", "MZN", "GMT+2")
        )
    }
}

