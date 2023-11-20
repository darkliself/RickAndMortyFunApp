package alexlissoft.rickandmortyfunapp.data.remote.episodes

import alexlissoft.rickandmortyfunapp.data.remote.Info
import com.google.gson.annotations.SerializedName

data class Episodes(
    val info: Info,
    @SerializedName("results")
    val listOfEpisodes: List<EpisodesInfo>
)