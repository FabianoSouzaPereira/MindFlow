package com.fabianospdev.mindflow.shared.models.relational.regions

import com.fabianospdev.mindflow.shared.entities.worldregions.AmericasEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmericasRelationalModel(
    @SerialName("northAmerica")
    override val northAmerica: List<CountryRelationalModel> = defaultNorthAmerica,

    @SerialName("southAmerica")
    override val southAmerica: List<CountryRelationalModel> = defaultSouthAmerica,

    @SerialName("centralAmerica")
    override val centralAmerica: List<CountryRelationalModel> = defaultCentralAmerica,

    @SerialName("caribbean")
    override val caribbean: List<CountryRelationalModel> = defaultCaribbean,
) : AmericasEntity {

    companion object {

        val defaultNorthAmerica = listOf(
            CountryRelationalModel("1", "USA", "English", "USD", "GMT-5"),
            CountryRelationalModel("2", "Canada", "English/French", "CAD", "GMT-6"),
            CountryRelationalModel("3", "Mexico", "Spanish", "MXN", "GMT-6")
        )

        val defaultSouthAmerica = listOf(
            CountryRelationalModel("4", "Brazil", "Portuguese", "BRL", "GMT-3"),
            CountryRelationalModel("5", "Argentina", "Spanish", "ARS", "GMT-3"),
            CountryRelationalModel("6", "Chile", "Spanish", "CLP", "GMT-4"),
            CountryRelationalModel("7", "Colombia", "Spanish", "COP", "GMT-5"),
            CountryRelationalModel("8", "Peru", "Spanish", "PEN", "GMT-5"),
            CountryRelationalModel("9", "Venezuela", "Spanish", "VES", "GMT-4")
        )

        val defaultCentralAmerica = listOf(
            CountryRelationalModel("10", "Guatemala", "Spanish", "GTQ", "GMT-6"),
            CountryRelationalModel("11", "Honduras", "Spanish", "HNL", "GMT-6"),
            CountryRelationalModel("12", "Panama", "Spanish", "PAB", "GMT-5"),
            CountryRelationalModel("13", "El Salvador", "Spanish", "USD", "GMT-6"),
            CountryRelationalModel("14", "Nicaragua", "Spanish", "NIO", "GMT-6"),
            CountryRelationalModel("15", "Costa Rica", "Spanish", "CRC", "GMT-6")
        )

        val defaultCaribbean = listOf(
            CountryRelationalModel("16", "Cuba", "Spanish", "CUP", "GMT-5"),
            CountryRelationalModel("17", "Dominican Republic", "Spanish", "DOP", "GMT-4"),
            CountryRelationalModel("18", "Jamaica", "English", "JMD", "GMT-5"),
            CountryRelationalModel("19", "Haiti", "French/Creole", "HTG", "GMT-5"),
            CountryRelationalModel("20", "Bahamas", "English", "BSD", "GMT-5"),
            CountryRelationalModel("21", "Barbados", "English", "BBD", "GMT-4"),
            CountryRelationalModel("22", "Trinidad and Tobago", "English", "TTD", "GMT-4")
        )
    }
}