package alexlissoft.rickandmortyfunapp.data.remote.locatons

import alexlissoft.rickandmortyfunapp.data.remote.Info
import com.google.gson.annotations.SerializedName

data class Locations(
    val info: Info,
    @SerializedName("results")
    val locationsList: List<LocationInfo>
)