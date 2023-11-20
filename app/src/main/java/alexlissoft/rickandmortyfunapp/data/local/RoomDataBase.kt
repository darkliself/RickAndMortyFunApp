package alexlissoft.rickandmortyfunapp.data.local

import alexlissoft.rickandmortyfunapp.data.local.dao.CharacterDao
import alexlissoft.rickandmortyfunapp.data.local.dao.EpisodeDao
import alexlissoft.rickandmortyfunapp.data.local.dao.LocationDao
import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.local.ralations.CharacterEpisodeCrossRef
import alexlissoft.rickandmortyfunapp.data.local.ralations.LocationCharacterCrossRef
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        CharacterEntity::class,
        EpisodeEntity::class,
        LocationEntity::class,
        CharacterEpisodeCrossRef::class,
        LocationCharacterCrossRef::class
    ],
    version = 1
)

abstract class RoomDataBase : RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val episodeDao: EpisodeDao
    abstract val locationDao: LocationDao
}
