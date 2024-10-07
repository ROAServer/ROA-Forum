package cn.mercury9.roa.forum.ui.window.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.mercury9.roa.forum.data.viewModel.AppViewModel
import cn.mercury9.roa.forum.data.viewModel.AppViewModel.AppLayoutType
import cn.mercury9.roa.forum.data.viewModel.window.main.MainWindowViewModel
import cn.mercury9.roa.forum.data.viewModel.window.main.NavTabItemInfo
import cn.mercury9.roa.forum.resources.Res
import cn.mercury9.roa.forum.resources.app_name
import cn.mercury9.roa.forum.resources.roa_normal_256x
import cn.mercury9.utils.compose.painter
import cn.mercury9.utils.compose.string

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainWindow(
  appViewModel: AppViewModel,
  mainWindowViewModel: MainWindowViewModel = viewModel { MainWindowViewModel() },
) {

  mainWindowViewModel.pagerState = rememberPagerState { mainWindowViewModel.tabList.size }

  @Composable
  fun Pages(page: Int) {
    mainWindowViewModel.TabPageComposeableOf(
      mainWindowViewModel.tabList[page],
      appViewModel,
    )
  }

  when (appViewModel.appLayoutType) {
    AppLayoutType.Expanded, AppLayoutType.Medium ->
      MainWindowComponents.AppHorizontalLayout(
        mainWindowViewModel.pagerState,
        mainWindowViewModel.tabItemList,
      ) { pageIndex ->
        Pages(pageIndex)
      }

    AppLayoutType.Compact ->
      MainWindowComponents.AppVerticalLayout(
        mainWindowViewModel.pagerState,
        mainWindowViewModel.tabItemList,
      ) { pageIndex ->
        Pages(pageIndex)
      }
  }
}

private object MainWindowComponents{

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  fun AppHorizontalLayout(
    pagerState: PagerState,
    tabItemList: List<NavTabItemInfo>,
    pagerContent: @Composable PagerScope.(page: Int) -> Unit,
  ) {
    Row {
      AppNavigationRail(pagerState, tabItemList)
      VerticalDivider()
      HorizontalPager(
        state = pagerState,
        pageContent = pagerContent,
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
      )
    }
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  fun AppVerticalLayout(
    pagerState: PagerState,
    tabItemList: List<NavTabItemInfo>,
    pagerContent: @Composable PagerScope.(page: Int) -> Unit,
  ) {
    Column {
      HorizontalPager(
        state = pagerState,
        pageContent = pagerContent,
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
      )
      HorizontalDivider()
      AppNavigationBar(pagerState, tabItemList)
    }
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  fun AppNavigationBar(
    pagerState: PagerState,
    tabItemList: List<NavTabItemInfo>,
  ) {
    var selectedPageIndex by remember { mutableStateOf(pagerState.currentPage) }
    LaunchedEffect(selectedPageIndex) {
      pagerState.animateScrollToPage(selectedPageIndex)
    }
    NavigationBar {
      for (index in tabItemList.indices) {
        val item = tabItemList[index]
        AppNavBarItem(
          item = item,
          selected = index == pagerState.currentPage,
        ) {
          selectedPageIndex = index
        }
      }
    }
  }

  @Composable
  fun RowScope.AppNavBarItem(
    item: NavTabItemInfo,
    selected: Boolean,
    onClick: () -> Unit,
  ) {
    NavigationBarItem(
      selected = selected,
      icon = {
        Icon(
          (if (selected) item.iconSelected ?: item.icon else item.icon).painter,
          item.label.string,
        )
      },
      label = { Text(item.label.string) },
      onClick = onClick,
    )
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  fun AppNavigationRail(
    pagerState: PagerState,
    tabItemList: List<NavTabItemInfo>,
  ) {
    var selectedPageIndex by remember { mutableStateOf(pagerState.currentPage) }
    LaunchedEffect(selectedPageIndex) {
      pagerState.animateScrollToPage(selectedPageIndex)
    }

    Surface(
      color = MaterialTheme.colorScheme.surface,
      modifier = Modifier
        .width(80.dp),
    ) {
      Column(modifier = Modifier) {
        Image(
          Res.drawable.roa_normal_256x.painter,
          Res.string.app_name.string,
          modifier =
          Modifier
            .fillMaxWidth(),
        )

        HorizontalDivider()

        NavigationRail(
          modifier = Modifier
            .padding(top = 8.dp),
        ) {
          for (index in tabItemList.indices) {
            val item = tabItemList[index]
            AppNavRailItem(
              item = item,
              selected = index == pagerState.currentPage,
            ) {
              selectedPageIndex = index
            }
          }
        }
      }
    }
  }

  @Composable
  fun AppNavRailItem(
    item: NavTabItemInfo,
    selected: Boolean,
    onClick: () -> Unit,
  ) {
    NavigationRailItem(
      selected = selected,
      icon = {
        Icon(
          (if (selected) item.iconSelected ?: item.icon else item.icon).painter,
          item.label.string,
        )
      },
      label = { Text(item.label.string) },
      onClick = onClick,
    )
  }
}