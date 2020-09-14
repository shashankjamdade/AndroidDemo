
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Location(@SerializedName("country")
                    var country: String? = "",
                    @SerializedName("city")
                    var city: String? = "",
                    @Embedded
                    @SerializedName("street")
                    var street: Street? = Street(),
                    @Embedded
                    @SerializedName("timezone")
                    var timezone: Timezone? = Timezone(),
                    @SerializedName("postcode")
                    var postcode: Int = 0,
                    @Embedded
                    @SerializedName("coordinates")
                    var coordinates: Coordinates? = Coordinates(),
                    @SerializedName("state")
                    var state: String? = "")
