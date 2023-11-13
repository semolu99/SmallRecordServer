package ConSilkTea.SmallRecordServer.common.authority

data class TokenInfo(
    val grantType: String,
    val accessToken: String
)