package alexlissoft.rickandmortyfunapp.ui.theme.screens.character_details

import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.ui.theme.screens.characters.CharactersViewModel
import android.telecom.Call.Details
import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterDetails(
    characterId: Int = 1
) {
    val viewModel = hiltViewModel<CharactersViewModel>()
    val character  = viewModel.loadCharactersById(listOf(characterId.toString())).collectAsState(
        initial = emptyList()
    )
    Text(text = character.value.toString())
    Button(onClick = {
        Log.d("look_for_some_thing", character.toString())
    }) {
        Text("Show some thing")
    }
}