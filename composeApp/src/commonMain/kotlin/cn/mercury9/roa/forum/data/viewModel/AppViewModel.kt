@file:Suppress("MemberVisibilityCanBePrivate")

package cn.mercury9.roa.forum.data.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import cn.mercury9.roa.forum.Constants
import cn.mercury9.roa.forum.data.config.AppConfig
import cn.mercury9.roa.forum.data.config.ThemeConfig
import cn.mercury9.roa.forum.data.storage.DataStorage
import cn.mercury9.roa.forum.data.storage.KVDataStorage
import cn.mercury9.roa.forum.data.viewModel.AppViewModel.AppWindowNavRoute.*
import cn.mercury9.roa.forum.ui.theme.AppTheme
import cn.mercury9.roa.forum.ui.window.main.MainWindow
import cn.mercury9.roa.forum.ui.window.settings.SettingsWindow

class AppViewModel : ViewModel() {
  private val appConfigManager: DataStorage<AppConfig> = KVDataStorage(
    "${Constants.APP_PACKAGE}:config.app:",
    AppConfig.serializer(),
  ) {
    AppConfig.default()
  }

  var appConfig: AppConfig
    get() = appConfigManager.get()
    set(value) { appConfigManager.set(value) }

  private val themeConfigManager: DataStorage<ThemeConfig> = KVDataStorage(
    "${Constants.APP_PACKAGE}:config.theme:",
    ThemeConfig.serializer()
  ) {
    ThemeConfig.default()
  }

  var themeConfig: ThemeConfig
    get() = themeConfigManager.get()
    set(value) {
      themeConfigManager.set(value)
      appTheme = value.appTheme
    }

  var appLayoutType: AppLayoutType by mutableStateOf(
    AppLayoutType.Compact
  )

  var appTheme: AppTheme by mutableStateOf(
    themeConfigManager.get().appTheme
  )
    private set

  lateinit var navController: NavHostController

  enum class AppLayoutType {
    Expanded,
    Medium,
    Compact,
  }

  enum class AppWindowNavRoute {
    Main,
    Settings,
  }

  companion object {
    @Composable
    fun ComposableOfRoute(route: AppWindowNavRoute, appViewModel: AppViewModel) {
      when (route) {
        Main -> MainWindow(appViewModel)
        Settings -> SettingsWindow(appViewModel)
      }
    }
  }
}
