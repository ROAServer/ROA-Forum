package cn.mercury9.roa.forum.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.window.DecoratedWindowScope
import org.jetbrains.jewel.window.TitleBar
import org.jetbrains.jewel.window.defaultTitleBarStyle
import org.jetbrains.jewel.window.newFullscreenControls
import org.jetbrains.jewel.window.styling.TitleBarColors
import org.jetbrains.jewel.window.styling.TitleBarStyle

@Composable
fun DecoratedWindowScope.AppTitleBar() {
  TitleBar(
    style = TitleBarStyle(
      colors = TitleBarColors(
        background = MaterialTheme.colorScheme.background,
        inactiveBackground = MaterialTheme.colorScheme.background,
        content = MaterialTheme.colorScheme.onBackground,
        border = MaterialTheme.colorScheme.outline,
        fullscreenControlButtonsBackground = MaterialTheme.colorScheme.background,
        titlePaneButtonHoveredBackground = MaterialTheme.colorScheme.background,
        titlePaneButtonPressedBackground = MaterialTheme.colorScheme.background,
        titlePaneCloseButtonHoveredBackground = MaterialTheme.colorScheme.background,
        titlePaneCloseButtonPressedBackground = MaterialTheme.colorScheme.background,
        iconButtonHoveredBackground = MaterialTheme.colorScheme.background,
        iconButtonPressedBackground = MaterialTheme.colorScheme.background,
        dropdownPressedBackground = MaterialTheme.colorScheme.background,
        dropdownHoveredBackground = MaterialTheme.colorScheme.background
      ),
      metrics = JewelTheme.defaultTitleBarStyle.metrics,
      icons = JewelTheme.defaultTitleBarStyle.icons,
      dropdownStyle = JewelTheme.defaultTitleBarStyle.dropdownStyle,
      iconButtonStyle = JewelTheme.defaultTitleBarStyle.iconButtonStyle,
      paneButtonStyle = JewelTheme.defaultTitleBarStyle.paneButtonStyle,
      paneCloseButtonStyle = JewelTheme.defaultTitleBarStyle.paneCloseButtonStyle
    ),
    modifier = Modifier
      .newFullscreenControls(),
  ) {
    WindowDraggableArea {
      Surface(color = MaterialTheme.colorScheme.background) {
        Row(
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(title)
        }
      }
    }
  }
}
