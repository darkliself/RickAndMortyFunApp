package alexlissoft.rickandmortyfunapp.ui.theme.screens.episodes

import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun Episodes(
    episodes: LazyPagingItems<EpisodeEntity>
) {
    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    val viewModel = hiltViewModel<MediatorViewModel>()
//    val z = viewModel.initTest().collectAsLazyPagingItems()
    LaunchedEffect(key1 = episodes.loadState) {
        if (episodes.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (episodes.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Test", color = Color.Black)
    }
    if (episodes.loadState.refresh is LoadState.Loading) {
        CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center)
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(episodes.itemCount) { index ->
                episodes[index]?.let { episode ->
                    Text(text = episode.episode)
                    Text(text = episode.name)
                    Text(text = episode.created)
                    Text(text = episode.airDate)
                    Text(text = episode.url)
                }
            }
            item {
                if (episodes.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}