package cn.mercury9.roa.forum

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cn.mercury9.roa.forum.data.viewModel.AppViewModel.AppLayoutType
import cn.mercury9.roa.forum.resources.Res
import cn.mercury9.roa.forum.resources.app_name
import cn.mercury9.roa.forum.resources.roa_normal_256x
import cn.mercury9.utils.compose.painter
import cn.mercury9.utils.compose.setMinimumSize
import cn.mercury9.utils.compose.string

fun main() = application {
  val windowState = rememberWindowState()
  Window(
    state = windowState,
    onCloseRequest = ::exitApplication,
    title = Res.string.app_name.string,
    icon = Res.drawable.roa_normal_256x.painter
  ) {
    setMinimumSize(
      width = 800.dp,
      height = 600.dp,
    )
    App(
      appLayoutType = AppLayoutType.Expanded
    )
  }
}
