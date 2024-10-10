package cn.mercury9.roa.forum.server.data.config

import kotlinx.serialization.Serializable
import cn.mercury9.roa.forum.Constants

@Serializable
data class ServerConfig(
  val hostIp: String,
  val port: Int,
) {
  companion object {
    fun default(): ServerConfig = ServerConfig(
      hostIp = Constants.Server.HOST,
      port = Constants.Server.PORT,
    )
  }
}
