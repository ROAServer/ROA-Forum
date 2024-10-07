package cn.mercury9.roa.forum

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

fun main() {
  embeddedServer(
    Netty,
    port = Constants.SERVER_PORT,
    host = "0.0.0.0",
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
