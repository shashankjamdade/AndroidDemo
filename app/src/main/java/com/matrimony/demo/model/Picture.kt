import com.google.gson.annotations.SerializedName

data class Picture(@SerializedName("thumbnail")
                   var thumbnail: String? = "",
                   @SerializedName("large")
                   var large: String? = "",
                   @SerializedName("medium")
                   var medium: String? = "")
