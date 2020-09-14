import com.google.gson.annotations.SerializedName

data class Name(@SerializedName("last")
                var last: String? = "",
                @SerializedName("title")
                var title: String? = "",
                @SerializedName("first")
                var first: String? = "")
