package cn.mercury9.roa.forum

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.intui.standalone.theme.IntUiTheme
import org.jetbrains.jewel.intui.standalone.theme.createDefaultTextStyle
import org.jetbrains.jewel.intui.standalone.theme.createEditorTextStyle
import org.jetbrains.jewel.intui.standalone.theme.darkThemeDefinition
import org.jetbrains.jewel.intui.standalone.theme.default
import org.jetbrains.jewel.intui.standalone.theme.lightThemeDefinition
import org.jetbrains.jewel.intui.window.decoratedWindow
import org.jetbrains.jewel.intui.window.styling.dark
import org.jetbrains.jewel.intui.window.styling.light
import org.jetbrains.jewel.ui.ComponentStyling
import org.jetbrains.jewel.window.DecoratedWindow
import org.jetbrains.jewel.window.styling.TitleBarStyle
import cn.mercury9.roa.forum.data.viewModel.AppViewModel.AppLayoutType
import cn.mercury9.roa.forum.resources.Res
import cn.mercury9.roa.forum.resources.app_name
import cn.mercury9.roa.forum.resources.roa_normal_256x
import cn.mercury9.roa.forum.ui.components.AppTitleBar
import cn.mercury9.roa.forum.ui.theme.AppTheme
import cn.mercury9.roa.forum.ui.theme.ThemeProvider
import cn.mercury9.utils.compose.painter
import cn.mercury9.utils.compose.setMinimumSize
import cn.mercury9.utils.compose.string

fun main() = application {
  val windowState = rememberWindowState()

  var appTheme: AppTheme by remember { mutableStateOf(AppTheme(false)) }

  val textStyle = JewelTheme.createDefaultTextStyle()
  val editorStyle = JewelTheme.createEditorTextStyle()

  val themeDefinition =
      if (appTheme.darkTheme) {
          JewelTheme.darkThemeDefinition(defaultTextStyle = textStyle, editorTextStyle = editorStyle)
      } else {
          JewelTheme.lightThemeDefinition(defaultTextStyle = textStyle, editorTextStyle = editorStyle)
      }

  ThemeProvider(
    appTheme = appTheme,
  ) {
    IntUiTheme(
      theme = themeDefinition,
      styling = ComponentStyling.default()
        .decoratedWindow(
          titleBarStyle = if (appTheme.darkTheme) TitleBarStyle.dark() else TitleBarStyle.light(),
        )
    ) {
      DecoratedWindow(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = Res.string.app_name.string,
        icon = Res.drawable.roa_normal_256x.painter,
      ) {
        setMinimumSize(
          width = 800.dp,
          height = 600.dp,
        )

        AppTitleBar()

        App(
          appLayoutType = AppLayoutType.Expanded,
          callbackAppTheme = {
            appTheme = it
          }
        )
      }
    }
  }
}
