package cn.mercury9.roa.forum.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cn.mercury9.roa.forum.ui.theme.amazingOrange.AmazingOrangeTheme

//@Serializable
enum class ThemeType {
  MaterialDefault, AmazingOrange
}

data class AppTheme(
  var darkTheme: Boolean,
  var themeType: ThemeType = ThemeType.MaterialDefault,
  var contrastType: ContrastType = ContrastType.Default,
)

@Composable
fun ThemeProvider(
  appTheme: AppTheme = AppTheme(darkTheme = isSystemInDarkTheme()),
  content: @Composable () -> Unit,
) {
  ThemeProvider(
    themeType = appTheme.themeType,
    contrastType = appTheme.contrastType,
    darkTheme = appTheme.darkTheme,
    content = content,
  )
}

@Composable
fun ThemeProvider(
  themeType: ThemeType = ThemeType.MaterialDefault,
  contrastType: ContrastType = ContrastType.Default,
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val theme = when (themeType) {
    ThemeType.MaterialDefault -> DefaultTheme
    ThemeType.AmazingOrange -> AmazingOrangeTheme
  }
  val colorScheme = theme.colorScheme(darkTheme, contrastType)

  val typography = theme.typography()

  MaterialTheme(
    colorScheme = colorScheme,
    typography = typography,
    content = content
  )
}
