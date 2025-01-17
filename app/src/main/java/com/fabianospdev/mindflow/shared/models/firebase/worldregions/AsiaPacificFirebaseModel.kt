package com.fabianospdev.mindflow.shared.models.firebase.worldregions

import com.fabianospdev.mindflow.shared.entities.worldregions.AsiaPacificEntity

data class AsiaPacificFirebaseModel(
    override val centralAsia: List<CountryFirebaseModel> = defaultCentralAsia,
    override val southAsia: List<CountryFirebaseModel> = defaultSouthAsia,
    override val northeastAsia: List<CountryFirebaseModel> = defaultNortheastAsia,
    override val southeastAsia: List<CountryFirebaseModel> = defaultSoutheastAsia,
    override val australia: List<CountryFirebaseModel> = defaultSoutheastAsia,
    override val oceania: List<CountryFirebaseModel> = defaultAustralia,
) : AsiaPacificEntity {
    companion object {
        val defaultCentralAsia = listOf(
            CountryFirebaseModel("1", "Kazakhstan", "Kazakh/Russian", "KZT", "GMT+6"),
            CountryFirebaseModel("2", "Uzbekistan", "Uzbek", "UZS", "GMT+5"),
            CountryFirebaseModel("3", "Turkmenistan", "Turkmen", "TMT", "GMT+5"),
            CountryFirebaseModel("4", "Kyrgyzstan", "Kyrgyz/Russian", "KGS", "GMT+6"),
            CountryFirebaseModel("5", "Tajikistan", "Tajik", "TJS", "GMT+5")
        )

        val defaultSouthAsia = listOf(
            CountryFirebaseModel("6", "India", "Hindi/English", "INR", "GMT+5:30"),
            CountryFirebaseModel("7", "Pakistan", "Urdu/English", "PKR", "GMT+5"),
            CountryFirebaseModel("8", "Bangladesh", "Bengali", "BDT", "GMT+6"),
            CountryFirebaseModel("9", "Sri Lanka", "Sinhala/Tamil", "LKR", "GMT+5:30"),
            CountryFirebaseModel("10", "Nepal", "Nepali", "NPR", "GMT+5:45")
        )

        val defaultNortheastAsia = listOf(
            CountryFirebaseModel("11", "China", "Mandarin", "CNY", "GMT+8"),
            CountryFirebaseModel("12", "Japan", "Japanese", "JPY", "GMT+9"),
            CountryFirebaseModel("13", "South Korea", "Korean", "KRW", "GMT+9"),
            CountryFirebaseModel("14", "North Korea", "Korean", "KPW", "GMT+9"),
            CountryFirebaseModel("15", "Mongolia", "Mongolian", "MNT", "GMT+8")
        )

        val defaultSoutheastAsia = listOf(
            CountryFirebaseModel("16", "Indonesia", "Indonesian", "IDR", "GMT+7"),
            CountryFirebaseModel("17", "Thailand", "Thai", "THB", "GMT+7"),
            CountryFirebaseModel("18", "Vietnam", "Vietnamese", "VND", "GMT+7"),
            CountryFirebaseModel("19", "Philippines", "Filipino/English", "PHP", "GMT+8"),
            CountryFirebaseModel("20", "Malaysia", "Malay", "MYR", "GMT+8")
        )

        val defaultAustralia = listOf(
            CountryFirebaseModel("21", "Australia", "English", "AUD", "GMT+10"),
            CountryFirebaseModel("22", "New Zealand", "English/Maori", "NZD", "GMT+12")
        )

        val defaultOceania = listOf(
            CountryFirebaseModel("23", "Fiji", "Fijian/English", "FJD", "GMT+12"),
            CountryFirebaseModel("24", "Papua New Guinea", "English/Tok Pisin", "PGK", "GMT+10"),
            CountryFirebaseModel("25", "Samoa", "Samoan/English", "WST", "GMT+13"),
            CountryFirebaseModel("26", "Tonga", "Tongan/English", "TOP", "GMT+13"),
            CountryFirebaseModel("27", "Vanuatu", "Bislama/English/French", "VUV", "GMT+11")
        )
    }
}