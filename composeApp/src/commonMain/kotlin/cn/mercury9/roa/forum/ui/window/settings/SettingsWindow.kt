package cn.mercury9.roa.forum.ui.window.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.skiko.hostOs
import cn.mercury9.roa.forum.data.viewModel.AppViewModel
import cn.mercury9.roa.forum.resources.Res
import cn.mercury9.roa.forum.resources.arrow_back_24px
import cn.mercury9.roa.forum.resources.arrow_back_ios_new_24px
import cn.mercury9.roa.forum.resources.button_back
import cn.mercury9.roa.forum.resources.label_setting_theme_dark
import cn.mercury9.roa.forum.resources.label_setting_theme_dark_follow_system_when_start
import cn.mercury9.roa.forum.resources.label_setting_theme_type
import cn.mercury9.roa.forum.resources.title_setting_theme
import cn.mercury9.roa.forum.resources.title_window_setting
import cn.mercury9.roa.forum.ui.theme.ContrastType
import cn.mercury9.roa.forum.ui.theme.ThemeType
import cn.mercury9.utils.compose.painter
import cn.mercury9.utils.compose.string
import cn.mercury9.utils.skiko.isApple

@Composable
fun SettingsWindow(
  appViewModel: AppViewModel
) {
  val backgroundColor by animateColorAsState(MaterialTheme.colorScheme.background)

  Scaffold(
    topBar = { SettingWindowComponents.SettingsWindowTopBar(appViewModel) },
  ) {
    Surface(
      color = backgroundColor,
      modifier = Modifier
        .fillMaxSize()
    ) {
      LazyColumn(
        contentPadding = PaddingValues(top = 80.dp)
      ) {
        item {
          SettingWindowComponents.Menu(
            title = Res.string.title_setting_theme.string
          ) {
            SettingWindowComponents.SwitchSetting(
              text = Res.string.label_setting_theme_dark_follow_system_when_start.string,
              checked = appViewModel.appConfig.followSystemDarkModeSettingWhenStartApp,
              onCheckedChange = {
                appViewModel.setAppConf(
                  appViewModel.appConfig.copy(
                    followSystemDarkModeSettingWhenStartApp = it
                  )
                )
              }
            )

            HorizontalDivider()

            SettingWindowComponents.SwitchSetting(
              text = Res.string.label_setting_theme_dark.string,
              checked = appViewModel.themeConfig.darkTheme,
              enabled = !appViewModel.appConfig.followSystemDarkModeSettingWhenStartApp,
              onCheckedChange = {
                appViewModel.setThemeConf(
                  appViewModel.themeConfig.copy(
                    darkTheme = it
                  )
                )
              }
            )

            SettingWindowComponents.DropDownSelectorSetting(
              text = Res.string.label_setting_theme_type.string,
              selections = ThemeType.entries.map { themeType ->
                SettingWindowComponents.DropDownSelection(
                  themeType.name,
                  appViewModel.themeConfig.themeType == themeType,
                ) {
                  appViewModel.setThemeConf(
                    appViewModel.themeConfig.copy(
                      themeType = themeType
                    )
                  )
                }
              }
            )

            SettingWindowComponents.DropDownSelectorSetting(
              text = Res.string.label_setting_theme_type.string,
              selections = ContrastType.entries.map { contrastType ->
                SettingWindowComponents.DropDownSelection(
                  contrastType.name,
                  appViewModel.themeConfig.contrastType == contrastType,
                ) {
                  appViewModel.setThemeConf(
                    appViewModel.themeConfig.copy(
                      contrastType = contrastType
                    )
                  )
                }
              }
            )
          }
        }
      }
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

  @Composable
  fun Menu(
    title: String,
    icon: DrawableResource? = null,
    content: @Composable (() -> Unit)
  ) {
    var showContent by remember { mutableStateOf(false) }
    val openIconRotation by animateFloatAsState(
      if (showContent) -90f else 0f,
    )
    Row(
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxWidth(),
    ) {
      Column(
        modifier = Modifier
          .wrapContentHeight()
          .widthIn(max = 500.dp)
          .fillMaxWidth()
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .height(48.dp)
            .clickable { showContent = !showContent }
        ) {
          if (icon != null) {
            Icon(
              icon.painter,
              title,
              Modifier.padding(8.dp)
            )
          }
          Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
          )
          Spacer(modifier = Modifier.weight(1f))
          Icon(
            Res.drawable.arrow_back_ios_new_24px.painter,
            null,
            modifier = Modifier
              .padding(8.dp)
              .rotate(openIconRotation)
          )
        }

        AnimatedVisibility(
          showContent,
          modifier = Modifier
            .padding(horizontal = 16.dp)
        ) {
          Column {
            content()
          }
        }
      }
    }
  }

  @Composable
  fun SwitchSetting(
    text: String,
    icon: DrawableResource? = null,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
    ) {
      if (icon != null) {
        Icon(
          icon.painter,
          text,
          modifier = Modifier.padding(8.dp),
        )
      }
      Text(
        text,
        modifier = Modifier.padding(8.dp),
      )
      Spacer(Modifier.weight(1f))
      Switch(
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        modifier = Modifier.padding(8.dp)
      )
    }
  }

  data class DropDownSelection(
    val label: String,
    val checked: Boolean,
    val onClicked: () -> Unit
  )

  @Composable
  fun DropDownSelectorSetting(
    text: String,
    icon: DrawableResource? = null,
    selections: List<DropDownSelection>,
  ) {
    var expanded by remember { mutableStateOf(false) }
    val selected = try {
      selections.filter { it.checked } [0].label
    } catch (e: Exception) {
      "--"
    }
    val availableSelections = selections.filter { !it.checked }

    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
    ) {
      if (icon != null) {
        Icon(
          icon.painter,
          text,
          modifier = Modifier.padding(8.dp),
        )
      }
      Text(
        text,
        modifier = Modifier.padding(8.dp),
      )
      Spacer(Modifier.weight(1f))

      Card {
        Box(
          modifier = Modifier
            .wrapContentSize()
            .clickable { expanded = !expanded }
        ) {
          Text(
            selected,
            modifier = Modifier.padding(8.dp),
          )
        }

        DropdownMenu(
          expanded,
          { expanded = false }
        ) {
          for (selection in availableSelections) {
            DropdownMenuItem(
              text = { Text(selection.label) },
              onClick = { selection.onClicked() }
            )
          }
        }
      }
    }
  }
}
