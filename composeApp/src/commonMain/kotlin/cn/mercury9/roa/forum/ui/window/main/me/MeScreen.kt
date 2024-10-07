package cn.mercury9.roa.forum.ui.window.main.me

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cn.mercury9.roa.forum.data.User
import cn.mercury9.roa.forum.data.config.ThemeConfig.Companion.toThemeConfig
import cn.mercury9.roa.forum.data.viewModel.AppViewModel
import cn.mercury9.roa.forum.resources.Res
import cn.mercury9.roa.forum.resources.apparel_24px
import cn.mercury9.roa.forum.resources.check_24px
import cn.mercury9.roa.forum.resources.dark_mode_24px
import cn.mercury9.roa.forum.resources.hint_user_bio_empty
import cn.mercury9.roa.forum.resources.light_mode_24px
import cn.mercury9.roa.forum.resources.roa_normal_256x
import cn.mercury9.roa.forum.resources.settings_24px
import cn.mercury9.roa.forum.resources.title_window_setting
import cn.mercury9.roa.forum.ui.theme.ThemeType
import cn.mercury9.utils.compose.painter
import cn.mercury9.utils.compose.string

@Composable
fun MeScreen(
  appViewModel: AppViewModel
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
      .padding(8.dp),
  ) {
    MeScreenComponionts.QuickSettingsBar(appViewModel)
    MeScreenComponionts.BasicUserInfo(
      User(
        0uL, "example_user",
        "Example User",
        null, null,
        "Just an example user.\n" + "E-mail: example_user@example.com\n" + "Phone: 555-1919-8100",
      )
    )
    MeScreenComponionts.QuickActionsList(appViewModel)
  }
}

private object MeScreenComponionts {
  @Composable
  fun QuickSettingsBar(
    appViewModel: AppViewModel
  ) {
    Row(
      horizontalArrangement = Arrangement.End,
      modifier = Modifier
        .fillMaxWidth()
    ) {
      var showThemeChooser by remember { mutableStateOf(false) }
      IconButton({showThemeChooser = true}) {
        Icon(
          Res.drawable.apparel_24px.painter,
          null
        )

        DropdownMenu(
          showThemeChooser,
          { showThemeChooser = false },
        ) {
          for (theme in ThemeType.entries) {
            val selected = appViewModel.appTheme.themeType == theme
            DropdownMenuItem(
              text = { Text(text = theme.toString()) },
              enabled = !selected,
              trailingIcon = {
                if (selected) {
                  Icon(Res.drawable.check_24px.painter, contentDescription = null)
                }
              },
              onClick = {
                appViewModel.themeConfig = appViewModel.appTheme.copy(
                  themeType = theme
                ).toThemeConfig()
              }
            )
          }
        }
      } // end: choose theme

      IconButton({
          appViewModel.themeConfig = appViewModel.appTheme.copy(
            darkTheme = !appViewModel.appTheme.darkTheme
          ).toThemeConfig()
      }) {
        Icon(
          painter = (
            if (appViewModel.appTheme.darkTheme) {
              Res.drawable.light_mode_24px
            } else {
              Res.drawable.dark_mode_24px
            }
          ).painter,
          contentDescription = null,
        )
      } // end: switch dark / light
    }
  }

  @Composable
  fun BasicUserInfo(user: User) {
    ElevatedCard {
      Column(
        modifier = Modifier
          .width(500.dp)
          .padding(32.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
          Image(
            Res.drawable.roa_normal_256x.painter,
            null,
            modifier = Modifier
              .size(100.dp)
              .clip(CircleShape)
              .border(4.dp, MaterialTheme.colorScheme.outline, CircleShape)
              .background(Color.Gray),
          )
          Text(user.displayName, style = MaterialTheme.typography.titleLarge)
        }
        UserBio(user.bio ?: Res.string.hint_user_bio_empty.string)
      }
    }
  }

  @Composable
  fun UserBio(text: String?) {
    Row(
      modifier = Modifier
        .height(IntrinsicSize.Min)
        .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
      VerticalDivider(Modifier.padding(8.dp))
      Box(
        Modifier
          .height(MaterialTheme.typography.labelLarge.lineHeight.value.dp * 3)
      ) {
        SelectionContainer {
          Text(
            text = text?: Res.string.hint_user_bio_empty.string,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            lineHeight = MaterialTheme.typography.labelLarge.lineHeight,
            modifier = Modifier
              .width(300.dp)
              .wrapContentHeight()
              .heightIn(max = MaterialTheme.typography.labelLarge.lineHeight.value.dp * 3)
              .align(Alignment.CenterStart)
          )
        }
      }
      VerticalDivider(Modifier.padding(8.dp))
    }
  }

  @Composable
  fun QuickActionsList(
    appViewModel: AppViewModel,
  ) {
    LazyColumn(
      modifier = Modifier
        .width(500.dp)
        .padding(horizontal = 16.dp),
    ) {
      item {
        QuickActionItem(
          Res.string.title_window_setting.string,
          Res.drawable.settings_24px.painter,
        ) {
          appViewModel.navController.navigate(AppViewModel.AppWindowNavRoute.Settings.name)
        }
      }
    }
  }

  @Composable
  private fun QuickActionItem(
      label: String,
      icon: Painter? = null,
      enable: Boolean = true,
      onClick: () -> Unit,
  ) {
    Box(Modifier
      .fillMaxWidth()
      .clickable(enable) { onClick() }
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
      ) {
        if (icon != null) {
          Icon(
            icon,
            label,
            Modifier.padding(8.dp),
          )
        }

        Text(label)
      }
    }
  }
}


