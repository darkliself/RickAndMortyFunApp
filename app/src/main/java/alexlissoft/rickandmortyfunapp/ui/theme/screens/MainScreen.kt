package alexlissoft.rickandmortyfunapp.ui.theme.screens

import alexlissoft.rickandmortyfunapp.ui.theme.screens.location.Locations
import alexlissoft.rickandmortyfunapp.ui.theme.screens.location.MediatorViewModel
import alexlissoft.rickandmortyfunapp.ui.theme.screens.navigation.Navigation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MediatorViewModel>()
    val beers = viewModel.locationPagingFlow.collectAsLazyPagingItems()
    val navController  = rememberNavController()

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)

    }
    val items = listOf(
        BottomNavBarItem(
            title = "one",
            selectedIcon = null,
            unselectedIcon = null,
            route = "characters"
        ),
        BottomNavBarItem(
            title = "two",
            selectedIcon = null,
            unselectedIcon = null,
            route = "episodes"
        ),
        BottomNavBarItem(
            title = "three",
            selectedIcon = null,
            unselectedIcon = null,
            route = "locations"
        ),
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route)
                            },
                            label = {
                                Text(text = item.title)
                            },
                            alwaysShowLabel = false,
                            icon = {}
                        )
                    }

                }
            }
        ) { padding ->
            Box (modifier = Modifier
                .fillMaxSize()
                .padding(padding)){
                Navigation(navController)
            }
        }
    }
}


data class BottomNavBarItem(
    val title: String,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    val route: String
)