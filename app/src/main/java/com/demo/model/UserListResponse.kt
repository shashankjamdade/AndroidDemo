package com.demo.model


import androidx.room.*
import com.demo.db.Converters
import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("results")
    var results: List<ResultUserItem>? = ArrayList()
)

@Entity
@TypeConverters(Converters::class)
data class ResultUserItem(
    @PrimaryKey
    var userId: String,
    @SerializedName("nat")
    var nat: String? = "",
    var userChoice: String? = "",
    @SerializedName("gender")
    var gender: String? = "",
    @SerializedName("phone")
    var phone: String? = "",
    @Embedded
    @SerializedName("dob")
    var dob: Dob? = Dob(),
    @Embedded
    @SerializedName("name")
    var name: Name? = Name(),
    @Embedded
    @SerializedName("registered")
    var registered: Registered? = Registered(),
    @Embedded
    @SerializedName("location")
    var location: Location? = Location(),
    @Embedded
    @SerializedName("id")
    var id: Id? = Id(),
    @Embedded
    @SerializedName("login")
    var login: Login? = Login(),
    @SerializedName("cell")
    var cell: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @Embedded
    @SerializedName("picture")
    var picture: Picture? = Picture()
)

data class Coordinates(
    @SerializedName("latitude")
    val latitude: String? = "",
    @SerializedName("longitude")
    val longitude: String? = ""
)


data class Login(
    @SerializedName("sha1")
    val sha: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("salt")
    val salt: String? = "",
    @SerializedName("sha256")
    val sha256: String? = "",
    @SerializedName("uuid")
    val uuid: String? = "",
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("md5")
    val md: String? = ""
)


data class Info(
    @SerializedName("seed")
    val seed: String? = "",
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: String? = "",
    @SerializedName("version")
    val version: String? = ""
)


data class Name(
    @SerializedName("last")
    val last: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("first")
    var first: String? = ""
)


data class Dob(
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("age")
    val age: String? = ""
)


data class Picture(
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("large")
    val large: String? = "",
    @SerializedName("medium")
    val medium: String? = ""
)


data class Street(
    @SerializedName("number")
    val number: String? = "",
    @ColumnInfo(name="street_name")
    @SerializedName("name")
    val name: String? = ""
)


data class Id(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("value")
    val value: String? = ""
)


data class Registered(
    @ColumnInfo(name = "regi_date")
    @SerializedName("date")
    val date: String? = "",
    @ColumnInfo(name = "regi_age")
    @SerializedName("age")
    val age: String? = ""
)


data class Location(
    @SerializedName("country")
    val country: String = "",
    @SerializedName("city")
    val city: String = "",
    @Embedded
    @SerializedName("street")
    val street: Street? = Street(),
    @Embedded
    @SerializedName("timezone")
    val timezone: Timezone = Timezone(),
    @SerializedName("postcode")
    val postcode: String? = "",
    @Embedded
    @SerializedName("coordinates")
    val coordinates: Coordinates? = Coordinates(),
    @SerializedName("state")
    val state: String? = ""
)


data class Timezone(
    @SerializedName("offset")
    var offset: String? = "",
    @SerializedName("description")
    var description: String? = ""
)








