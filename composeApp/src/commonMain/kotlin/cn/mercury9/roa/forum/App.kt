package cn.mercury9.roa.forum

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.mercury9.roa.forum.data.viewModel.AppViewModel
import cn.mercury9.roa.forum.data.viewModel.AppViewModel.AppLayoutType
import cn.mercury9.roa.forum.ui.theme.AppTheme
import cn.mercury9.roa.forum.ui.theme.ThemeProvider
import cn.mercury9.roa.forum.ui.window.WindowNavigation

@Composable
fun App(
  appViewModel: AppViewModel = viewModel { AppViewModel() },
  appLayoutType: AppLayoutType = AppLayoutType.Compact,
  appInsetTop: Dp = 0.dp,
  callbackAppTheme: (AppTheme) -> Unit = {},
) {
  appViewModel.appLayoutType = appLayoutType

  if (appViewModel.appConfig.followSystemDarkModeSettingWhenStartApp) {
    appViewModel.setThemeConf(appViewModel.themeConfig.copy(
      darkTheme = isSystemInDarkTheme(),
    ))
  }

  ThemeProvider(
    appViewModel.themeConfig.appTheme,
  ) {
    callbackAppTheme(appViewModel.themeConfig.appTheme)

    val backgroundColor by animateColorAsState(MaterialTheme.colorScheme.background)
    Surface(color = backgroundColor) {
      Column {
        if (appInsetTop.value > 0) {
          Spacer(Modifier.fillMaxWidth().height(appInsetTop - 1.dp))
          HorizontalDivider()
        }
        WindowNavigation(appViewModel)
      }
    }
  }
}
