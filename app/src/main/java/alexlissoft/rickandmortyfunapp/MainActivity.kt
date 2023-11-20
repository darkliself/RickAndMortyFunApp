package alexlissoft.rickandmortyfunapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alexlissoft.rickandmortyfunapp.ui.theme.RickAndMortyFunAppTheme
import alexlissoft.rickandmortyfunapp.ui.theme.screens.MainScreen
import alexlissoft.rickandmortyfunapp.ui.theme.screens.location.Locations
import alexlissoft.rickandmortyfunapp.ui.theme.screens.location.MediatorViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContent {
            RickAndMortyFunAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMortyFunAppTheme {
        Greeting("Android")
    }
}