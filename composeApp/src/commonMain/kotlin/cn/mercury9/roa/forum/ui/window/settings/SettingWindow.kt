package cn.mercury9.roa.forum.ui.window.settings

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cn.mercury9.roa.forum.data.viewModel.AppViewModel

@Composable
fun SettingWindow(
  appViewModel: AppViewModel
) {
  Button(
    {
      appViewModel.navController.navigateUp()
    }
  ) {
    Text("Back")
  }
}
