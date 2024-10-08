package cn.mercury9.roa.forum.data.storage.manager

import kotlinx.serialization.KSerializer
import kotlin.properties.ReadWriteProperty

interface StorageManager {
  fun <T> getValueOrNull(
    serializer: KSerializer<T>,
    key: String,
  ): T?

  fun <T> setValue(
    serializer: KSerializer<T>,
    key: String,
    value: T,
  )

  fun <T> value(
    serializer: KSerializer<T>,
    key: String,
    defaultValue: T
  ): ReadWriteProperty<Any?, T>

  fun hasKey(key: String): Boolean

  fun <T> remove(serializer: KSerializer<T>, key: String)

  val keys: Set<String>

  val size: Int
}
