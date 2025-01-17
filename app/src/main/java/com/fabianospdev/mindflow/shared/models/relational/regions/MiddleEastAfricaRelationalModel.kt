package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.MiddleEastAfricaEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MiddleEastAfricaRelationalModel(
    @SerialName("MiddleEast")
    override val middleEast: List<CountryRelationalModel> = defaultMiddleEast,

    @SerialName("NorthAfrica")
    override val northAfrica: List<CountryRelationalModel> = defaultNorthAfrica,

    @SerialName("SouthernAfrica")
    override val southernAfrica: List<CountryRelationalModel> = defaultSouthernAfrica,
) : MiddleEastAfricaEntity {
    companion object {
        val defaultMiddleEast = listOf(
            CountryRelationalModel("1", "Saudi Arabia", "Arabic", "SAR", "GMT+3"),
            CountryRelationalModel("2", "Iran", "Persian", "IRR", "GMT+3:30"),
            CountryRelationalModel("3", "Iraq", "Arabic/Kurdish", "IQD", "GMT+3"),
            CountryRelationalModel("4", "Israel", "Hebrew", "ILS", "GMT+2"),
            CountryRelationalModel("5", "Jordan", "Arabic", "JOD", "GMT+2")
        )

        val defaultNorthAfrica = listOf(
            CountryRelationalModel("6", "Egypt", "Arabic", "EGP", "GMT+2"),
            CountryRelationalModel("7", "Algeria", "Arabic/Berber", "DZD", "GMT+1"),
            CountryRelationalModel("8", "Morocco", "Arabic/Berber", "MAD", "GMT+0"),
            CountryRelationalModel("9", "Tunisia", "Arabic", "TND", "GMT+1"),
            CountryRelationalModel("10", "Libya", "Arabic", "LYD", "GMT+2")
        )

        val defaultSouthernAfrica = listOf(
            CountryRelationalModel("11", "South Africa", "English/Zulu/Xhosa", "ZAR", "GMT+2"),
            CountryRelationalModel("12", "Botswana", "English/Setswana", "BWP", "GMT+2"),
            CountryRelationalModel("13", "Namibia", "English/Afrikaans", "NAD", "GMT+2"),
            CountryRelationalModel("14", "Zimbabwe", "English/Shona", "ZWL", "GMT+2"),
            CountryRelationalModel("15", "Mozambique", "Portuguese", "MZN", "GMT+2")
        )
    }
}
