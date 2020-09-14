import com.google.gson.annotations.SerializedName

data class Login(@SerializedName("sha1")
                 var sha1: String? = "",
                 @SerializedName("password")
                 var password: String? = "",
                 @SerializedName("salt")
                 var salt: String? = "",
                 @SerializedName("sha256")
                 var sha256: String? = "",
                 @SerializedName("uuid")
                 var uuid: String? = "",
                 @SerializedName("username")
                 var username: String? = "",
                 @SerializedName("md5")
                 var md: String? = "")