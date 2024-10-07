package cn.mercury9.roa.forum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import cn.mercury9.roa.forum.data.viewModel.AppViewModel.AppLayoutType

class MainActivity : ComponentActivity() {

  @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()

    setContent {

      val windowSizeClass = calculateWindowSizeClass(this)

      val insetTop = with(LocalDensity.current) {
        WindowInsets.statusBars.getTop(LocalDensity.current).toDp()
      }

      App(
        appLayoutType =
        when (windowSizeClass.widthSizeClass) {
          WindowWidthSizeClass.Expanded -> AppLayoutType.Expanded
          WindowWidthSizeClass.Medium -> AppLayoutType.Medium
          else -> AppLayoutType.Compact
        },
        appInsetTop = insetTop
      )
    }
  }
}

@Preview
@Composable
fun AppAndroidPreview() {
  App()
}
