package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.EuropeEntity

data class EuropeFirebaseModel(
    override val northernEurope: List<CountryFirebaseModel> = defaultNorthernEurope,
    override val southernEurope: List<CountryFirebaseModel> = defaultSouthernEurope,
    override val easternEurope: List<CountryFirebaseModel> = defaultEasternEurope,
    override val westernEurope: List<CountryFirebaseModel> = defaultWesternEurope,
) : EuropeEntity {
    companion object {
        val defaultNorthernEurope = listOf(
            CountryFirebaseModel("1", "Norway", "Norwegian", "NOK", "GMT+1"),
            CountryFirebaseModel("2", "Sweden", "Swedish", "SEK", "GMT+1"),
            CountryFirebaseModel("3", "Finland", "Finnish", "EUR", "GMT+2"),
            CountryFirebaseModel("4", "Denmark", "Danish", "DKK", "GMT+1"),
            CountryFirebaseModel("5", "Iceland", "Icelandic", "ISK", "GMT+0")
        )

        val defaultSouthernEurope = listOf(
            CountryFirebaseModel("6", "Italy", "Italian", "EUR", "GMT+1"),
            CountryFirebaseModel("7", "Spain", "Spanish", "EUR", "GMT+1"),
            CountryFirebaseModel("8", "Portugal", "Portuguese", "EUR", "GMT+0"),
            CountryFirebaseModel("9", "Greece", "Greek", "EUR", "GMT+2"),
            CountryFirebaseModel("10", "Croatia", "Croatian", "HRK", "GMT+1")
        )

        val defaultEasternEurope = listOf(
            CountryFirebaseModel("11", "Poland", "Polish", "PLN", "GMT+1"),
            CountryFirebaseModel("12", "Romania", "Romanian", "RON", "GMT+2"),
            CountryFirebaseModel("13", "Czech Republic", "Czech", "CZK", "GMT+1"),
            CountryFirebaseModel("14", "Hungary", "Hungarian", "HUF", "GMT+1"),
            CountryFirebaseModel("15", "Ukraine", "Ukrainian", "UAH", "GMT+2")
        )

        val defaultWesternEurope = listOf(
            CountryFirebaseModel("16", "Germany", "German", "EUR", "GMT+1"),
            CountryFirebaseModel("17", "France", "French", "EUR", "GMT+1"),
            CountryFirebaseModel("18", "United Kingdom", "English", "GBP", "GMT+0"),
            CountryFirebaseModel("19", "Belgium", "Dutch/French", "EUR", "GMT+1"),
            CountryFirebaseModel("20", "Netherlands", "Dutch", "EUR", "GMT+1")
        )
    }
}