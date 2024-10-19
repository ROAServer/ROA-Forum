@file:Suppress("MemberVisibilityCanBePrivate")

package cn.mercury9.roa.forum.data.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import cn.mercury9.roa.forum.Constants
import cn.mercury9.roa.forum.data.client.HttpService
import cn.mercury9.roa.forum.data.config.AppConfig
import cn.mercury9.roa.forum.data.config.ThemeConfig
import cn.mercury9.roa.forum.data.storage.DataStorage
import cn.mercury9.roa.forum.data.storage.DefaultDataStorage
import cn.mercury9.roa.forum.data.viewModel.AppViewModel.AppWindowNavRoute.*
import cn.mercury9.roa.forum.ui.window.main.MainWindow
import cn.mercury9.roa.forum.ui.window.settings.SettingsWindow

class AppViewModel : ViewModel() {
  private val appConfigManager: DefaultDataStorage<AppConfig> = DefaultDataStorage(
    "${Constants.App.PACKAGE}:config.app:",
    AppConfig.serializer(),
  ) {
    AppConfig.default()
  }

  var appConfig by mutableStateOf(appConfigManager.get())
    private set

  fun setAppConf(config: AppConfig) {
    appConfigManager.set(config)
    appConfig = appConfigManager.get()
  }

  private val themeConfigManager: DataStorage<ThemeConfig> = DefaultDataStorage(
    "${Constants.App.PACKAGE}:config.theme:",
    ThemeConfig.serializer(),
  ) {
    ThemeConfig.default()
  }

  var themeConfig: ThemeConfig by mutableStateOf(themeConfigManager.get())
    private set

  fun setThemeConf(config: ThemeConfig) {
    themeConfigManager.set(config)
    themeConfig = themeConfigManager.get()
  }

  var appLayoutType: AppLayoutType by mutableStateOf(
    AppLayoutType.Compact
  )

  lateinit var navController: NavHostController

  val httpService = HttpService()

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
