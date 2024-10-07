package cn.mercury9.roa.forum

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform
