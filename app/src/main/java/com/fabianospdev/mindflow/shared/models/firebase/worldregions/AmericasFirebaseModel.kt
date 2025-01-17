package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.AmericasEntity

data class AmericasFirebaseModel(
    override val northAmerica: List<CountryFirebaseModel> = defaultNorthAmerica,
    override val southAmerica: List<CountryFirebaseModel> = defaultSouthAmerica,
    override val centralAmerica: List<CountryFirebaseModel> = defaultCentralAmerica,
    override val caribbean: List<CountryFirebaseModel> = defaultCaribbean,
) : AmericasEntity {

    companion object {

        val defaultNorthAmerica = listOf(
            CountryFirebaseModel("1", "USA", "English", "USD", "GMT-5"),
            CountryFirebaseModel("2", "Canada", "English/French", "CAD", "GMT-6"),
            CountryFirebaseModel("3", "Mexico", "Spanish", "MXN", "GMT-6")
        )

        val defaultSouthAmerica = listOf(
            CountryFirebaseModel("4", "Brazil", "Portuguese", "BRL", "GMT-3"),
            CountryFirebaseModel("5", "Argentina", "Spanish", "ARS", "GMT-3"),
            CountryFirebaseModel("6", "Chile", "Spanish", "CLP", "GMT-4"),
            CountryFirebaseModel("7", "Colombia", "Spanish", "COP", "GMT-5"),
            CountryFirebaseModel("8", "Peru", "Spanish", "PEN", "GMT-5"),
            CountryFirebaseModel("9", "Venezuela", "Spanish", "VES", "GMT-4")
        )

        val defaultCentralAmerica = listOf(
            CountryFirebaseModel("10", "Guatemala", "Spanish", "GTQ", "GMT-6"),
            CountryFirebaseModel("11", "Honduras", "Spanish", "HNL", "GMT-6"),
            CountryFirebaseModel("12", "Panama", "Spanish", "PAB", "GMT-5"),
            CountryFirebaseModel("13", "El Salvador", "Spanish", "USD", "GMT-6"),
            CountryFirebaseModel("14", "Nicaragua", "Spanish", "NIO", "GMT-6"),
            CountryFirebaseModel("15", "Costa Rica", "Spanish", "CRC", "GMT-6")
        )

        val defaultCaribbean = listOf(
            CountryFirebaseModel("16", "Cuba", "Spanish", "CUP", "GMT-5"),
            CountryFirebaseModel("17", "Dominican Republic", "Spanish", "DOP", "GMT-4"),
            CountryFirebaseModel("18", "Jamaica", "English", "JMD", "GMT-5"),
            CountryFirebaseModel("19", "Haiti", "French/Creole", "HTG", "GMT-5"),
            CountryFirebaseModel("20", "Bahamas", "English", "BSD", "GMT-5"),
            CountryFirebaseModel("21", "Barbados", "English", "BBD", "GMT-4"),
            CountryFirebaseModel("22", "Trinidad and Tobago", "English", "TTD", "GMT-4")
        )
    }
}