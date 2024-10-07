package cn.mercury9.roa.forum.data.config

import kotlinx.serialization.Serializable
import cn.mercury9.roa.forum.ui.theme.AppTheme
import cn.mercury9.roa.forum.ui.theme.ContrastType
import cn.mercury9.roa.forum.ui.theme.ThemeType

@Serializable
data class ThemeConfig(
  val themeType: ThemeType,
  val darkTheme: Boolean,
  val contrastType: ContrastType,
) {
  val appTheme get() = AppTheme(darkTheme, themeType, contrastType)

  companion object {
    fun default(): ThemeConfig = ThemeConfig(
      themeType = ThemeType.AmazingOrange,
      darkTheme = true,
      contrastType = ContrastType.Default
    )

    fun AppTheme.toThemeConfig() = ThemeConfig(
      themeType,
      darkTheme,
      contrastType,
    )
  }
}
