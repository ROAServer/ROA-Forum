@file:Suppress("MemberVisibilityCanBePrivate")

package cn.mercury9.roa.forum.data.storage

import kotlinx.serialization.KSerializer
import cn.mercury9.roa.forum.data.storage.manager.DefaultStorageManager
import cn.mercury9.roa.forum.data.storage.manager.StorageManager
import cn.mercury9.roa.forum.data.storage.manager.getValueAndInit

class KVDataStorage<T>(
  val key: String,
  private val serializer: KSerializer<T>,
  private val storageManager: StorageManager = DefaultStorageManager(),
  override var onValueSet: (value: T) -> Unit = {},
  val defaultValue: () -> T,
): DataStorage<T> {

  override fun get(): T {
    return storageManager.getValueAndInit(
      serializer = serializer,
      key = key,
      defaultValue = defaultValue(),
    )
  }

  override fun set(value: T) {
    storageManager.setValue(
      serializer = serializer,
      key = key,
      value = value,
    )
    onValueSet(value)
  }
}
