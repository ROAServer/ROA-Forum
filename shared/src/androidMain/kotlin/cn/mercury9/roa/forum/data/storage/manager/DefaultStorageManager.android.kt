@file:Suppress("RedundantVisibilityModifier", "unused")

package cn.mercury9.roa.forum.data.storage.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.startup.Initializer
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

private var appContext: Context? = null

actual fun getSettings(): Settings {
  val appContext = appContext!!

  val preferencesName = "${appContext.packageName}_preferences"
  val delegate: SharedPreferences =
    appContext.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

  return SharedPreferencesSettings(delegate)
}

public class SettingsInitializer : Initializer<Context> {
  override fun create(context: Context): Context =
    context.applicationContext.also { appContext = it }

  override fun dependencies(): List<Class<out Initializer<*>>> =
    emptyList()
}
