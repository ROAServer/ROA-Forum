package cn.mercury9.roa.forum.data.storage.manager

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual fun getSettings(): Settings {
  val delegate = NSUserDefaults.standardUserDefaults
  return NSUserDefaultsSettings(delegate)
}
