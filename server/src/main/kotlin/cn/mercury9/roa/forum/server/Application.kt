package cn.mercury9.roa.forum.server

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.digest
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import net.peanuuutz.tomlkt.Toml
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import cn.mercury9.roa.forum.Greeting
import cn.mercury9.roa.forum.server.data.config.ServerConfig

fun main() {

  val configPath = Path("./.serverConfig")
  configPath.createDirectories()

  val configFile = configPath
    .resolve("config.toml")
    .toFile()

  if (configFile.createNewFile()) {
    configFile.writeText(
      Toml.encodeToString(
        ServerConfig.serializer(),
        ServerConfig.default()
      )
    )
  }
  val config: ServerConfig =
    Toml.decodeFromString(ServerConfig.serializer(), configFile.readText())

  embeddedServer(
    Netty,
    port = config.port,
    host = config.hostIp,
    module = Application::module
  ).start(wait = true)
}

fun Application.module() {

  install(Authentication) {
    digest("user") {

    }
  }

  routing {
    get("/") {
      call.respondText("Ktor: ${Greeting().greet()}")
    }
  }

}
