import com.google.gson.annotations.SerializedName


data class Id(@SerializedName("name")
              var name: String? = "",
              @SerializedName("varue")
              var varue: String? = "")


