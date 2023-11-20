package alexlissoft.rickandmortyfunapp.data.local.ralations

import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class EpisodeWithCharacter(
    @Embedded val episodeId: EpisodeEntity,
    @Relation(
        parentColumn = "episode_id",
        entityColumn = "character_id",
        associateBy = Junction(CharacterEpisodeCrossRef::class)
    )
    val characterId: List<CharacterEntity>
)