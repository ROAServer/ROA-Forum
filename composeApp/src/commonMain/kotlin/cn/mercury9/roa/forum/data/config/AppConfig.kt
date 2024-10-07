package cn.mercury9.roa.forum.data.config

import kotlinx.serialization.Serializable

@Serializable
data class AppConfig(
  var followSystemDarkModeSettingWhenStartApp: Boolean,
) {
  companion object {
    fun default(): AppConfig = AppConfig(
      followSystemDarkModeSettingWhenStartApp = false
    )
  }
}
