@file:Suppress("unused")

package cn.mercury9.roa.forum.data.storage.manager

import kotlinx.serialization.KSerializer

inline fun <T> StorageManager.getValue(
  serializer: KSerializer<T>,
  key: String,
  defaultValue: T,
): T {
  return getValueOrNull(
    serializer = serializer,
    key = key
  ) ?: defaultValue
}

/**
 * If value is null, return default value and set it.
 */
inline fun <T> StorageManager.getValueAndInit(
  serializer: KSerializer<T>,
  key: String,
  defaultValue: T,
): T {
  return getValueOrNull(
    serializer = serializer,
    key = key
  ) ?: run {
    setValue(
      serializer = serializer,
      key = key,
      value = defaultValue
    )

    defaultValue
  }
}
