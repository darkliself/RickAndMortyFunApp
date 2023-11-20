package alexlissoft.rickandmortyfunapp.ui.theme.screens.navigation

import alexlissoft.rickandmortyfunapp.ui.theme.screens.character_details.CharacterDetails
import alexlissoft.rickandmortyfunapp.ui.theme.screens.characters.Characters
import alexlissoft.rickandmortyfunapp.ui.theme.screens.characters.CharactersViewModel
import alexlissoft.rickandmortyfunapp.ui.theme.screens.episodes.Episodes
import alexlissoft.rickandmortyfunapp.ui.theme.screens.episodes.EpisodesViewModel
import alexlissoft.rickandmortyfunapp.ui.theme.screens.location.Locations
import alexlissoft.rickandmortyfunapp.ui.theme.screens.location.MediatorViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Characters.route,
    ) {


        composable(
            route = Screen.Characters.route,
        ) {
            val viewModel = hiltViewModel<CharactersViewModel>()
            val characters = viewModel.charactersPagingFlow.collectAsLazyPagingItems()
            Characters(characters, navController)
        }
        composable(
            route = Screen.Episodes.route,
        ) {
            val viewModel = hiltViewModel<EpisodesViewModel>()
            val episodes = viewModel.episodesPagingFlow.collectAsLazyPagingItems()
            Episodes(episodes)
//                        val viewModel = hiltViewModel<CharactersViewModel>()
//            val characters = viewModel.charactersPagingFlow.collectAsLazyPagingItems()
//            Characters(characters)
        }
        composable(
            route = Screen.Locations.route,
        ) {
            val viewModel = hiltViewModel<MediatorViewModel>()
            val locations = viewModel.locationPagingFlow.collectAsLazyPagingItems()
            Locations(locations)
        }
        composable(
            route = Screen.CharacterDetails.route,
        ) {
            CharacterDetails()
        }

    }
}

//fun NavGraphBuilder.composableSlideHorizontally(
//    route: String,
//    screen: @Composable () -> Unit
//) {
//    composable(
//        route = route,
//        enterTransition = {
//            slideIntoContainer(
//                AnimatedContentTransitionScope.SlideDirection.Left,
//                animationSpec = tween(700)
//            )
//        },
//        exitTransition = {
//            slideOutOfContainer(
//                AnimatedContentTransitionScope.SlideDirection.Left,
//                animationSpec = tween(700)
//            )
//        },
//
//        popEnterTransition = {
//
//            slideIntoContainer(
//                AnimatedContentTransitionScope.SlideDirection.Right,
//                animationSpec = tween(700)
//            )
//        },
//        popExitTransition = {
//            slideOutOfContainer(
//                AnimatedContentTransitionScope.SlideDirection.Right,
//                animationSpec = tween(700)
//            )
//        },
//        content = { screen() }
//    )
//}
