package cn.mercury9.roa.forum.data.storage.manager

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.serialization.removeValue
import com.russhwolf.settings.serialization.serializedValue
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlin.properties.ReadWriteProperty

class DefaultStorageManager(
  val settings: Settings = getSettings()
) : StorageManager {
  @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
  override fun <T> getValueOrNull(
    serializer: KSerializer<T>,
    key: String
  ): T? = settings.decodeValueOrNull(
    serializer = serializer,
    key = key
  )

  @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
  override fun <T> setValue(
    serializer: KSerializer<T>,
    key: String,
    value: T
  ) = settings.encodeValue(
    serializer = serializer,
    key = key,
    value = value,
  )

  @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
  override fun <T> value(
    serializer: KSerializer<T>,
    key: String,
    defaultValue: T
  ): ReadWriteProperty<Any?, T> = settings.serializedValue(
    serializer = serializer,
    key = key,
    defaultValue = defaultValue
  )

  override fun hasKey(key: String): Boolean =
    settings.hasKey(key)

  @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
  override fun <T> remove(serializer: KSerializer<T>, key: String) =
    settings.removeValue(serializer, key)

  override val keys: Set<String>
    get() = settings.keys

  override val size: Int
    get() = settings.size

}
