package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.EuropeEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EuropeRelationalModel(
    @SerialName("NorthernEurope")
    override val northernEurope: List<CountryRelationalModel> = defaultNorthernEurope,

    @SerialName("SouthernEurope")
    override val southernEurope: List<CountryRelationalModel> = defaultSouthernEurope,

    @SerialName("EasternEurope")
    override val easternEurope: List<CountryRelationalModel> = defaultEasternEurope,

    @SerialName("WesternEurope")
    override val westernEurope: List<CountryRelationalModel> = defaultWesternEurope,
) : EuropeEntity {
    companion object {
        val defaultNorthernEurope = listOf(
            CountryRelationalModel("1", "Norway", "Norwegian", "NOK", "GMT+1"),
            CountryRelationalModel("2", "Sweden", "Swedish", "SEK", "GMT+1"),
            CountryRelationalModel("3", "Finland", "Finnish", "EUR", "GMT+2"),
            CountryRelationalModel("4", "Denmark", "Danish", "DKK", "GMT+1"),
            CountryRelationalModel("5", "Iceland", "Icelandic", "ISK", "GMT+0")
        )

        val defaultSouthernEurope = listOf(
            CountryRelationalModel("6", "Italy", "Italian", "EUR", "GMT+1"),
            CountryRelationalModel("7", "Spain", "Spanish", "EUR", "GMT+1"),
            CountryRelationalModel("8", "Portugal", "Portuguese", "EUR", "GMT+0"),
            CountryRelationalModel("9", "Greece", "Greek", "EUR", "GMT+2"),
            CountryRelationalModel("10", "Croatia", "Croatian", "HRK", "GMT+1")
        )

        val defaultEasternEurope = listOf(
            CountryRelationalModel("11", "Poland", "Polish", "PLN", "GMT+1"),
            CountryRelationalModel("12", "Romania", "Romanian", "RON", "GMT+2"),
            CountryRelationalModel("13", "Czech Republic", "Czech", "CZK", "GMT+1"),
            CountryRelationalModel("14", "Hungary", "Hungarian", "HUF", "GMT+1"),
            CountryRelationalModel("15", "Ukraine", "Ukrainian", "UAH", "GMT+2")
        )

        val defaultWesternEurope = listOf(
            CountryRelationalModel("16", "Germany", "German", "EUR", "GMT+1"),
            CountryRelationalModel("17", "France", "French", "EUR", "GMT+1"),
            CountryRelationalModel("18", "United Kingdom", "English", "GBP", "GMT+0"),
            CountryRelationalModel("19", "Belgium", "Dutch/French", "EUR", "GMT+1"),
            CountryRelationalModel("20", "Netherlands", "Dutch", "EUR", "GMT+1")
        )
    }
}
