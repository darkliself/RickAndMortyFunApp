package alexlissoft.rickandmortyfunapp.data.local.ralations

import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class LocationWithCharacters(
    @Embedded val locationId: LocationEntity,
    @Relation(
        parentColumn = "location_id",
        entityColumn = "character_id",
        associateBy = Junction(LocationCharacterCrossRef::class)
    )
    val characterId: List<CharacterEntity>
)