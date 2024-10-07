package cn.mercury9.roa.forum.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
  val id: ULong,
  val username: String,
  val displayName: String,
  val avatarUrl: String?,
  val headerImageUrl: String?,
  val bio: String?,
)
