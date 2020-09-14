import com.google.gson.annotations.SerializedName

data class Timezone(@SerializedName("offset")
                    var offset: String? = "",
                    @SerializedName("description")
                    var description: String? = "")
