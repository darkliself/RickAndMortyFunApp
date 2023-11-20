package alexlissoft.rickandmortyfunapp.ui.theme.screens.characters

import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.ui.theme.screens.navigation.Screen
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun Characters(
    characters: LazyPagingItems<CharacterEntity>,
    navController: NavController
) {
    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
    val viewModel = hiltViewModel<CharactersViewModel>()
//    val z = viewModel.initTest().collectAsLazyPagingItems()
    LaunchedEffect(key1 = characters.loadState) {
        if (characters.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (characters.loadState.refresh as LoadState.Error).error.message,
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
    if (characters.loadState.refresh is LoadState.Loading) {
        CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center)
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(characters.itemCount) { index ->
                characters[index]?.let { character ->
                    Card(
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.CharacterDetails.route)
                        }
                    ) {
                        Text(text = character.characterId.toString())
                        Text(text = character.name)
                        Text(text = character.created)
                        Text(text = character.gender)
                        Text(text = character.url)
                        Text(text = character.species)
                        Text(text = character.status)
                    }
                }
            }
            item {
                if (characters.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}