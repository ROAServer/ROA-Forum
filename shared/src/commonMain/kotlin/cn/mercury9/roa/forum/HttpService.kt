package cn.mercury9.roa.forum

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.DigestAuthCredentials
import io.ktor.client.plugins.auth.providers.digest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

object HttpService {
  private var client: HttpClient = HttpClient()

  fun resetClient() {
    client.close()
    client = HttpClient()
  }

  fun loginTo(
    targetRealm: String,
    username: String,
    passwordHashedBase64: String,
  ) {
    client.config {
      install(Auth) {
        digest {
          credentials {
            DigestAuthCredentials(
              username = username,
              password = passwordHashedBase64,
            )
          }
          realm = targetRealm
        }
      }
    }
  }

  suspend fun get(
    url: Url,
    builder: HttpRequestBuilder.() -> Unit = {},
  ): HttpResponse =
    withContext(Dispatchers.IO) {
      client.get(url, builder)
    }
}
