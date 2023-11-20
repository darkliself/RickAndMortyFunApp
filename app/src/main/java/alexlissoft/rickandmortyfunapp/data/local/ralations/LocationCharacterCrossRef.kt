package alexlissoft.rickandmortyfunapp.data.local.ralations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["location_id", "character_id"])
data class LocationCharacterCrossRef(
    @ColumnInfo(name = "location_id")
    val locationId: Int,
    @ColumnInfo(name = "character_id")
    val characterId: Int,
)
