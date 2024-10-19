package cn.mercury9.roa.forum.data.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.cookies.HttpCookies

class HttpService(
  httpClient: (() -> HttpClient) = {
    HttpClient {
      install(HttpCookies)
    }
  },
) {
  private val initHttpClient = httpClient

  var client: HttpClient = httpClient().config {
    install(HttpCookies)
  }
    private set

  fun resetClient() {
    this.client = initHttpClient()
  }
}
