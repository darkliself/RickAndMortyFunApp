package alexlissoft.rickandmortyfunapp.data.remote.api

import alexlissoft.rickandmortyfunapp.data.remote.characters.CharacterInfo
import alexlissoft.rickandmortyfunapp.data.remote.characters.Characters
import alexlissoft.rickandmortyfunapp.data.remote.episodes.Episodes
import alexlissoft.rickandmortyfunapp.data.remote.episodes.EpisodesInfo
import alexlissoft.rickandmortyfunapp.data.remote.locatons.LocationInfo
import alexlissoft.rickandmortyfunapp.data.remote.locatons.Locations
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApiInterface {
    @GET("character/")
    suspend fun getCharactersList(
        @Query("page")
        page: Int
    ): Characters

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id")
        id: Int
    ): CharacterInfo

    @GET("character/{ids},")
    suspend fun getCharactersByIDs(
        @Path("ids") ids: String
    ): List<CharacterInfo>

    @GET("character/")
    suspend fun searchCharacters(
        @Query("name")
        name: String?,
        @Query("status")
        status: String?,
        @Query("species")
        species: String?,
        @Query("type")
        type: String?,
        @Query("gender")
        gender: String?,
    ): Characters

    @GET("episode/")
    suspend fun getEpisodesList(
        @Query("page")
        page: Int
    ): Episodes

    @GET("episode/{id}")
    suspend fun getEpisodesDetails(
        @Path("id") id: Int
    ): EpisodesInfo

    @GET("episode/{ids},")
    suspend fun getEpisodesByID(
        @Path("ids") ids: String
    ): List<EpisodesInfo>

    @GET("episode/")
    suspend fun searchEpisodes(
        @Query("name")
        name: String?,
        @Query("episode")
        episode: String?
    ): Episodes

    @GET("location/")
    suspend fun getLocationsList(
        @Query("page")
        page: Int
    ): Locations

    @GET("location/{id}")
    suspend fun getLocationDetails(
        @Path("id")
        id: Int
    ): LocationInfo

    @GET("location/")
    suspend fun searchLocations(
        @Query("name")
        name: String?,
        @Query("type")
        type: String?,
        @Query("dimension")
        dimension: String?,
    ): Locations
}
