package alexlissoft.rickandmortyfunapp.data.repository

import alexlissoft.rickandmortyfunapp.data.remote.Info
import alexlissoft.rickandmortyfunapp.data.remote.api.RetrofitApiInterface
import alexlissoft.rickandmortyfunapp.data.remote.characters.CharacterInfo
import alexlissoft.rickandmortyfunapp.data.remote.characters.Characters
import alexlissoft.rickandmortyfunapp.data.remote.episodes.Episodes
import alexlissoft.rickandmortyfunapp.data.remote.episodes.EpisodesInfo
import alexlissoft.rickandmortyfunapp.data.remote.locatons.LocationInfo
import alexlissoft.rickandmortyfunapp.data.remote.locatons.Locations
import javax.inject.Inject

class Repository @Inject constructor(
    private val rickAndMortyApiService: RetrofitApiInterface,
//    private val room: RoomWIthRetrofitInteraction
) {

    suspend fun getCharacters(page: Int): Characters {
        val result = rickAndMortyApiService.getCharactersList(page)
//        room.saveToDataBaseCharacters(result.listOfCharacters)
        return result
    }

    suspend fun getCharactersByIDs(ids: String): List<CharacterInfo> {
        val result = rickAndMortyApiService.getCharactersByIDs(ids)
//        room.saveToDataBaseCharacters(result)
        return result
    }

    suspend fun searchCharacters(
        query: CharterSearchQuery
    ): Characters {
        var result: Characters
        try {
            result = rickAndMortyApiService.searchCharacters(
                name = query.name,
                status = query.status,
                species = query.species,
                type = query.type,
                gender = query.gender
            )
//            room.saveToDataBaseCharacters(result.listOfCharacters)
        } catch (e: Exception) {
            result = Characters(
                info = Info(
                    count = 0,
                    next = "",
                    pages = 0,
                    prev = ""
                ), listOfCharacters = listOf()
            )
        }
        return result
    }

    suspend fun getCharacterDetails(id: Int): CharacterInfo {
        return rickAndMortyApiService.getCharacterDetails(id)
    }

    suspend fun getEpisodes(page: Int): Episodes {
        val result = rickAndMortyApiService.getEpisodesList(page)
//        room.saveToDataBaseEpisodes(result.listOfEpisodes)
        return result
    }

    suspend fun getEpisodesByID(idList: List<Int>): List<EpisodesInfo> {
        val result = rickAndMortyApiService.getEpisodesByID(idList.joinToString(","))
//        room.saveToDataBaseEpisodes(result)
        return result
    }

    suspend fun getEpisodeDetail(id: Int): EpisodesInfo {
        return rickAndMortyApiService.getEpisodesDetails(id)
    }

    suspend fun searchEpisodes(
        name: String?,
        episode: String?
    ): Episodes? {
        var result: Episodes?
        try {
            result = rickAndMortyApiService.searchEpisodes(name, episode)
//            room.saveToDataBaseEpisodes(result.listOfEpisodes)
        } catch (e: Exception) {
            result = Episodes(
                info = Info(
                    count = 0,
                    next = "",
                    pages = 0,
                    prev = ""
                ), listOfEpisodes = listOf()
            )
        }
        return result
    }

    suspend fun getLocations(page: Int): Locations {
        val result = rickAndMortyApiService.getLocationsList(page)
//        room.saveToDataBaseLocations(result.locationsList)
        return rickAndMortyApiService.getLocationsList(page)
    }

    suspend fun getLocationDetails(id: Int): LocationInfo {
        return rickAndMortyApiService.getLocationDetails(id)
    }

    suspend fun searchLocations(
        name: String?,
        type: String?,
        dimension: String?,
    ): Locations {
        var result: Locations
        try {
            result = rickAndMortyApiService.searchLocations(name, type, dimension)
//            room.saveToDataBaseLocations(result.locationsList)
        } catch (e: Exception) {
            result = Locations(
                info = Info(
                    count = 0,
                    next = "",
                    pages = 0,
                    prev = ""
                ), locationsList = listOf()
            )
        }
        return result
    }
}

data class CharterSearchQuery(
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    var gender: String? = null
)

fun CharterSearchQuery.noneToNull(): CharterSearchQuery {
    val noneGender = if (gender == "none") null else gender
    val noneStatus = if (status == "none") null else status
    return CharterSearchQuery(
        name = name,
        status = noneStatus,
        species = species,
        type = type,
        gender = noneGender,
    )
}