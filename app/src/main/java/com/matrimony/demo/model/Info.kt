import com.google.gson.annotations.SerializedName

data class Info(@SerializedName("seed")
                var seed: String? = "",
                @SerializedName("page")
                var page: Int = 0,
                @SerializedName("results")
                var results: Int = 0,
                @SerializedName("version")
                var version: String? = "")
