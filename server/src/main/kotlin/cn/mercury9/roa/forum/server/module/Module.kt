package cn.mercury9.roa.forum.server.module

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.digest
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import cn.mercury9.roa.forum.Greeting

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
