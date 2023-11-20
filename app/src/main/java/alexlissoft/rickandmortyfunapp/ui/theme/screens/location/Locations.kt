package alexlissoft.rickandmortyfunapp.ui.theme.screens.location

import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Locations(
    locations: LazyPagingItems<LocationEntity>
) {
    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    val viewModel = hiltViewModel<MediatorViewModel>()
//    val z = viewModel.initTest().collectAsLazyPagingItems()
    LaunchedEffect(key1 = locations.loadState) {
        if (locations.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (locations.loadState.refresh as LoadState.Error).error.message,
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
    if (locations.loadState.refresh is LoadState.Loading) {
        CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center)
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(locations.itemCount) { index ->
                locations[index]?.let { location ->
                    Text(text = location.locationId.toString())
                    Text(text = location.name)
                    Text(text = location.created)
                    Text(text = location.dimension)
                }
            }
//                    items(z.itemCount) {  index ->
//                        locations[index]?.let { location ->
//                            Text(text = location.locationId.toString())
//                            Text(text = location.name)
//                            Text(text = location.created)
//                            Text(text = location.dimension)
//                        }
//                    }

            item {
                if (locations.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
