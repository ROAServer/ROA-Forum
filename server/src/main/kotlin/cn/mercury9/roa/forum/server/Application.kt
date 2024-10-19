package cn.mercury9.roa.forum.server

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import net.peanuuutz.tomlkt.Toml
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import cn.mercury9.roa.forum.server.data.config.ServerConfig
import cn.mercury9.roa.forum.server.module.module

fun main() {
  val config: ServerConfig = loadServerConfig()

  embeddedServer(
    Netty,
    port = config.port,
    host = config.hostIp,
    module = Application::module
  ).start(wait = true)
}

private fun loadServerConfig(): ServerConfig {
  println("Loading server config...")

  val configPath = Path("./.serverConfig")
  configPath.createDirectories()

  val configFile = configPath
    .resolve("config.toml")
    .toFile()

  if (configFile.createNewFile()) {
    println("Config file not found, initiating default config...")
    configFile.writeText(
      Toml.encodeToString(
        ServerConfig.serializer(),
        ServerConfig.default()
      )
    )
  }

  println("Server config loaded!")

  return Toml.decodeFromString(ServerConfig.serializer(), configFile.readText())
}
