@file:Suppress("MemberVisibilityCanBePrivate")

package cn.mercury9.roa.forum.data.storage

import kotlinx.serialization.KSerializer
import kotlin.properties.ReadWriteProperty
import cn.mercury9.roa.forum.data.storage.manager.DefaultStorageManager
import cn.mercury9.roa.forum.data.storage.manager.StorageManager
import cn.mercury9.roa.forum.data.storage.manager.getValueAndInit

class DefaultDataStorage<T>(
  val key: String,
  val serializer: KSerializer<T>,
  val storageManager: StorageManager = DefaultStorageManager(),
  val defaultValue: () -> T,
): DataStorage<T> {

  override fun get(): T =
    storageManager.getValueAndInit(
      serializer = serializer,
      key = key,
      defaultValue = defaultValue(),
    )

  override fun set(value: T) =
    storageManager.setValue(
      serializer = serializer,
      key = key,
      value = value,
    )

  override fun value(): ReadWriteProperty<Any?, T> = storageManager.value(
    serializer = serializer,
    key = key,
    defaultValue = defaultValue(),
  )

}
