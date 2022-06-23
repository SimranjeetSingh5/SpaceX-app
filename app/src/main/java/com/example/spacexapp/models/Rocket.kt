package com.example.spacexapp.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "rockets_table")
data class Rocket(

    @SerializedName("height"           ) var height         : Height?                   = Height(),
    @SerializedName("diameter"         ) var diameter       : Diameter?                 = Diameter(),
    @SerializedName("engines"          ) var engines        : Engines?                  = Engines(),
    @SerializedName("flickr_images"    ) var flickrImages   : MutableList<String>         = mutableListOf(),
    @SerializedName("name"             ) var name           : String?                   = null,
    @SerializedName("type"             ) var type           : String?                   = null,
    @SerializedName("active"           ) var active         : Boolean?                  = null,
    @SerializedName("stages"           ) var stages         : Double?                      = null,
    @SerializedName("boosters"         ) var boosters       : Double?                      = null,
    @SerializedName("cost_per_launch"  ) var costPerLaunch  : Double?                      = null,
    @SerializedName("success_rate_pct" ) var successRatePct : Double?                      = null,
    @SerializedName("first_flight"     ) var firstFlight    : String?                   = null,
    @SerializedName("country"          ) var country        : String?                   = null,
    @SerializedName("company"          ) var company        : String?                   = null,
    @SerializedName("wikipedia"        ) var wikipedia      : String?                   = null,
    @SerializedName("description"      ) var description    : String?                   = null,
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @SerializedName("id"               ) var id             : String
): Serializable
data class Height (

    @SerializedName("meters" ) var meters : Double? = null,
    @SerializedName("feet"   ) var feet   : Double?    = null

): Serializable
data class Diameter (

    @SerializedName("meters" ) var meters : Double? = null,
    @SerializedName("feet"   ) var feet   : Double? = null

): Serializable
data class Engines (

    @SerializedName("number"           ) var number         : Double?            = null,
    @SerializedName("type"             ) var type           : String?         = null,
    @SerializedName("version"          ) var version        : String?         = null,
    @SerializedName("layout"           ) var layout         : String?         = null,
    @SerializedName("engine_loss_max"  ) var engineLossMax  : Double?            = null,
    @SerializedName("propellant_1"     ) var propellant1    : String?         = null,
    @SerializedName("propellant_2"     ) var propellant2    : String?         = null,
    @SerializedName("thrust_to_weight" ) var thrustToWeight : Double?            = null

): Serializable
data class Source(
    val id: Any,
    val name: String
):Serializable