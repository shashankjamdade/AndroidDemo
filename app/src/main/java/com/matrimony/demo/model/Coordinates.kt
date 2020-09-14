import com.google.gson.annotations.SerializedName

data class Coordinates(@SerializedName("latitude")
                       var latitude: String? = "",
                       @SerializedName("longitude")
                       var longitude: String? = "")


