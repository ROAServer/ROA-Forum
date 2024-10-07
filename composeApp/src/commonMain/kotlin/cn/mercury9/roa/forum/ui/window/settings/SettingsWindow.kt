package cn.mercury9.roa.forum.ui.window.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.skiko.hostOs
import cn.mercury9.roa.forum.data.viewModel.AppViewModel
import cn.mercury9.roa.forum.resources.Res
import cn.mercury9.roa.forum.resources.arrow_back_24px
import cn.mercury9.roa.forum.resources.arrow_back_ios_new_24px
import cn.mercury9.roa.forum.resources.button_back
import cn.mercury9.roa.forum.resources.title_window_setting
import cn.mercury9.utils.compose.painter
import cn.mercury9.utils.compose.string
import cn.mercury9.utils.skiko.isApple

@Composable
fun SettingsWindow(
  appViewModel: AppViewModel
) {
  Scaffold(
    topBar = { SettingWindowComponents.SettingsWindowTopBar(appViewModel) },
  ) {
    LazyColumn(
      modifier = Modifier.fillMaxSize()
    ) {

    }
  }
}

private object SettingWindowComponents {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  inline fun SettingsWindowTopBar(
    appViewModel: AppViewModel,
  ) {
    TopAppBar(
      title = {
        Text(
          text = Res.string.title_window_setting.string,
          style = MaterialTheme.typography.titleLarge,
        )
      },
      navigationIcon = {
        IconButton({
          appViewModel.navController.navigateUp()
        }) {
          Icon(
            painter = if (hostOs.isApple()) {
              Res.drawable.arrow_back_ios_new_24px.painter
            } else {
              Res.drawable.arrow_back_24px.painter
            },
            contentDescription = Res.string.button_back.string,
          )
        }
      },
    )
  }
}
