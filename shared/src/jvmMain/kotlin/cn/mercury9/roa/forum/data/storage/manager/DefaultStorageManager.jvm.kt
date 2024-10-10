package cn.mercury9.roa.forum.data.storage.manager

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences
import cn.mercury9.roa.forum.Constants

actual fun getSettings(): Settings {
  val delegate: Preferences =
    Preferences.userRoot().node(Constants.App.PACKAGE.replace('.', '/'))
  return PreferencesSettings(delegate)
}
