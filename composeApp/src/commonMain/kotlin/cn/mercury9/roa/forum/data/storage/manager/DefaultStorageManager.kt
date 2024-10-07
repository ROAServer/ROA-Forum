package cn.mercury9.roa.forum.data.storage.manager

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.serialization.removeValue
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer

class DefaultStorageManager(
  private val settings: Settings = getSettings()
) : StorageManager {
  @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
  override fun <T> getValueOrNull(
    serializer: KSerializer<T>,
    key: String
  ): T? {
    return settings.decodeValueOrNull(
      serializer = serializer,
      key = key
    )
  }

  @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
  override fun <T> setValue(
    serializer: KSerializer<T>,
    key: String,
    value: T
  ) {
    settings.encodeValue(
      serializer = serializer,
      key = key,
      value = value,
    )
  }

  override fun hasKey(key: String): Boolean {
    return settings.hasKey(key)
  }

  @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
  override fun <T> remove(serializer: KSerializer<T>, key: String) {
    settings.removeValue(serializer, key)
  }

  override val keys: Set<String>
    get() = settings.keys

  override val size: Int
    get() = settings.size

}
