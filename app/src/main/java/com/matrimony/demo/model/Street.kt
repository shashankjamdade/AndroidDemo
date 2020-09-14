import com.google.gson.annotations.SerializedName

data class Street(@SerializedName("number")
                  var number: Int = 0,
                  @SerializedName("name")
                  var name: String? = "")


