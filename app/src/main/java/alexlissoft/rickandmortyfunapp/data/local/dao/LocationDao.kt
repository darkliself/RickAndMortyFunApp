package alexlissoft.rickandmortyfunapp.data.local.dao

import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.local.ralations.LocationCharacterCrossRef
import alexlissoft.rickandmortyfunapp.data.local.ralations.LocationWithCharacters
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface LocationDao {
    @Query("SELECT * FROM location_table")
    fun locationsPagingSource(): PagingSource<Int, LocationEntity>

    @Query("SELECT * FROM location_table WHERE location_table.name LIKE '%' || :name || '%'")
    fun searchEpisodesPagingSource(name: String): PagingSource<Int, LocationEntity>

    @Query("DELETE FROM location_table")
    fun nukeTable()

    @Query("SELECT * FROM location_table ORDER BY location_id")
    fun getAllLocations(): Flow<List<LocationEntity>>
    @Query("SELECT * FROM location_table ORDER BY location_id")
    fun getAllLocationsNotFlow(): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListOfLocations(episode: List<LocationEntity>)

    @Query("SELECT * FROM location_table WHERE location_id=:id")
    fun getLocationById(id:Int): LocationEntity

    @Query("SELECT * FROM location_table WHERE location_id IN(:id)")
    fun getLocationListById(id:List<String>): Flow<List<LocationEntity>>

    @Query(
        "SELECT * FROM location_table " +
                "WHERE (:name IS NULL OR name LIKE '%' || :name || '%') " +
                "AND (:type IS NULL OR type LIKE '%' || :type || '%') " +
                "AND (:dimension IS NULL OR dimension LIKE '%' || :dimension || '%') "
    )
    fun getFilteredLocations(
        name: String?,
        type: String?,
        dimension: String?,
    ): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocationCharacterCrossRef(crossRef: LocationCharacterCrossRef)

    @Transaction
    @Query("SELECT * FROM location_table WHERE location_id = :id")
    fun getLocationWithCharacter(id: Int): LocationWithCharacters
}
