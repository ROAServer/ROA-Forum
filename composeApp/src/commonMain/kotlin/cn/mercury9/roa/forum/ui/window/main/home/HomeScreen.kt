package cn.mercury9.roa.forum.ui.window.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.mercury9.roa.forum.data.viewModel.AppViewModel

@Composable
fun HomeScreen(
  appViewModel: AppViewModel,
) {
  LazyColumn(
    contentPadding = PaddingValues(vertical = 8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp)
  ) {
    items((1..3).toList()) {
      ElevatedCard {
        Column(
          modifier = Modifier
            .padding(16.dp)
        ) {
          Text(
            text = "Item $it",
            style = MaterialTheme.typography.titleLarge,
          )
          HorizontalDivider()
          Text(
            text = "$it",
          )
        }
      }
    }
  }
}
