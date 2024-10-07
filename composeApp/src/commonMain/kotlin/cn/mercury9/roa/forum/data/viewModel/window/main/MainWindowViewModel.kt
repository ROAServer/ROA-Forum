package cn.mercury9.roa.forum.data.viewModel.window.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import cn.mercury9.roa.forum.data.viewModel.AppViewModel
import cn.mercury9.roa.forum.resources.Res
import cn.mercury9.roa.forum.resources.home_24px
import cn.mercury9.roa.forum.resources.home_fill_24px
import cn.mercury9.roa.forum.resources.person_24px
import cn.mercury9.roa.forum.resources.person_fill_24px
import cn.mercury9.roa.forum.resources.title_screen_home
import cn.mercury9.roa.forum.resources.title_screen_me
import cn.mercury9.roa.forum.ui.window.main.home.HomeScreen
import cn.mercury9.roa.forum.ui.window.main.me.MeScreen

enum class MainWindowTab {
  Home,
  Me,
}

data class NavTabItemInfo(
  val label: StringResource,
  val icon: DrawableResource,
  val iconSelected: DrawableResource?,
  val composable: @Composable (AppViewModel) -> Unit = {},
)

class MainWindowViewModel : ViewModel() {
  private val tabPageMap = mutableStateMapOf(
    MainWindowTab.Home to
        NavTabItemInfo(
          Res.string.title_screen_home,
          Res.drawable.home_24px,
          Res.drawable.home_fill_24px,
        ) {
          HomeScreen(it)
        },
    MainWindowTab.Me to
        NavTabItemInfo(
          Res.string.title_screen_me,
          Res.drawable.person_24px,
          Res.drawable.person_fill_24px,
        ) {
          MeScreen(it)
        },
  )

  fun tabPageItemOf(tab: MainWindowTab): NavTabItemInfo =
    tabPageMap[tab]!!

  @Composable
  inline fun TabPageComposeableOf(tab: MainWindowTab, appViewModel: AppViewModel): Unit =
    tabPageItemOf(tab).composable(appViewModel)

  val tabList = mutableStateListOf(
      MainWindowTab.Home,
      MainWindowTab.Me,
    )

  @OptIn(ExperimentalFoundationApi::class)
  lateinit var pagerState: PagerState

  val tabItemList: List<NavTabItemInfo>
    get() {
      val list = mutableListOf<NavTabItemInfo>()
      for (tab in tabList) {
        tabPageMap[tab]?.let { list += it } ?: throw Error()
      }
      return list
    }
}
