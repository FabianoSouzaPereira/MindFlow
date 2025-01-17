package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.AsiaPacificEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AsiaPacificRelationalModel(
    @SerialName("CentralAsia")
    override val centralAsia: List<CountryRelationalModel> = defaultCentralAsia,

    @SerialName("SouthAsia")
    override val southAsia: List<CountryRelationalModel> = defaultSouthAsia,

    @SerialName("NortheastAsia")
    override val northeastAsia: List<CountryRelationalModel> = defaultNortheastAsia,

    @SerialName("SoutheastAsia")
    override val southeastAsia: List<CountryRelationalModel> = defaultSoutheastAsia,

    @SerialName("Australia")
    override val australia: List<CountryRelationalModel> = defaultAustralia,

    @SerialName("Oceania")
    override val oceania: List<CountryRelationalModel> = defaultOceania,
) : AsiaPacificEntity {
    companion object {
        val defaultCentralAsia = listOf(
            CountryRelationalModel("1", "Kazakhstan", "Kazakh/Russian", "KZT", "GMT+6"),
            CountryRelationalModel("2", "Uzbekistan", "Uzbek", "UZS", "GMT+5"),
            CountryRelationalModel("3", "Turkmenistan", "Turkmen", "TMT", "GMT+5"),
            CountryRelationalModel("4", "Kyrgyzstan", "Kyrgyz/Russian", "KGS", "GMT+6"),
            CountryRelationalModel("5", "Tajikistan", "Tajik", "TJS", "GMT+5")
        )

        val defaultSouthAsia = listOf(
            CountryRelationalModel("6", "India", "Hindi/English", "INR", "GMT+5:30"),
            CountryRelationalModel("7", "Pakistan", "Urdu/English", "PKR", "GMT+5"),
            CountryRelationalModel("8", "Bangladesh", "Bengali", "BDT", "GMT+6"),
            CountryRelationalModel("9", "Sri Lanka", "Sinhala/Tamil", "LKR", "GMT+5:30"),
            CountryRelationalModel("10", "Nepal", "Nepali", "NPR", "GMT+5:45")
        )

        val defaultNortheastAsia = listOf(
            CountryRelationalModel("11", "China", "Mandarin", "CNY", "GMT+8"),
            CountryRelationalModel("12", "Japan", "Japanese", "JPY", "GMT+9"),
            CountryRelationalModel("13", "South Korea", "Korean", "KRW", "GMT+9"),
            CountryRelationalModel("14", "North Korea", "Korean", "KPW", "GMT+9"),
            CountryRelationalModel("15", "Mongolia", "Mongolian", "MNT", "GMT+8")
        )

        val defaultSoutheastAsia = listOf(
            CountryRelationalModel("16", "Indonesia", "Indonesian", "IDR", "GMT+7"),
            CountryRelationalModel("17", "Thailand", "Thai", "THB", "GMT+7"),
            CountryRelationalModel("18", "Vietnam", "Vietnamese", "VND", "GMT+7"),
            CountryRelationalModel("19", "Philippines", "Filipino/English", "PHP", "GMT+8"),
            CountryRelationalModel("20", "Malaysia", "Malay", "MYR", "GMT+8")
        )

        val defaultAustralia = listOf(
            CountryRelationalModel("21", "Australia", "English", "AUD", "GMT+10"),
            CountryRelationalModel("22", "New Zealand", "English/Maori", "NZD", "GMT+12")
        )

        val defaultOceania = listOf(
            CountryRelationalModel("23", "Fiji", "Fijian/English", "FJD", "GMT+12"),
            CountryRelationalModel("24", "Papua New Guinea", "English/Tok Pisin", "PGK", "GMT+10"),
            CountryRelationalModel("25", "Samoa", "Samoan/English", "WST", "GMT+13"),
            CountryRelationalModel("26", "Tonga", "Tongan/English", "TOP", "GMT+13"),
            CountryRelationalModel("27", "Vanuatu", "Bislama/English/French", "VUV", "GMT+11")
        )
    }
}