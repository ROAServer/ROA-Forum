package cn.mercury9.roa.forum.ui.window

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.mercury9.roa.forum.data.viewModel.AppViewModel

@Composable
fun WindowNavigation(appViewModel: AppViewModel) {
  appViewModel.navController = rememberNavController()

  NavHost(
    navController = appViewModel.navController,
    startDestination = AppViewModel.AppWindowNavRoute.Main.name,
  ) {
    for (route in AppViewModel.AppWindowNavRoute.entries) {
      composable(route.name) {
        AppViewModel.ComposableOfRoute(route, appViewModel)
      }
    }
  }
}
