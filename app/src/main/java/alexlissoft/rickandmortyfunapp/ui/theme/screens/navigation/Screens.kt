package alexlissoft.rickandmortyfunapp.ui.theme.screens.navigation

sealed class Screen(val route: String) {
    object Characters : Screen("characters")

    //    object Locations : Screen("episodes")
    object Locations : Screen("locations")
    object Episodes : Screen("episodes")

    //    data object Episodes : Screen("episodes")
    object CharacterDetails : Screen("character_details")

}