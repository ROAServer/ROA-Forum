package cn.mercury9.roa.forum.utils

import io.ktor.util.encodeBase64
import org.kotlincrypto.hash.sha2.SHA256

fun String.sha256(): String {
  val digest = SHA256()
  digest.update(this.encodeToByteArray())
  val hashed = digest.digest()
  return hashed.encodeBase64()
}

operator fun String.times(n: Int): String {
  var string = ""
  for (i in 1..n) {
    string += this
  }
  return string
}
