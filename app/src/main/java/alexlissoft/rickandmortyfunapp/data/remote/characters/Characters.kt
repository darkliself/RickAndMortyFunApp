package alexlissoft.rickandmortyfunapp.data.remote.characters

import alexlissoft.rickandmortyfunapp.data.remote.Info
import alexlissoft.rickandmortyfunapp.data.remote.characters.CharacterInfo
import com.google.gson.annotations.SerializedName

data class Characters(
    val info: Info,
    @SerializedName("results")
    val listOfCharacters: List<CharacterInfo>
)