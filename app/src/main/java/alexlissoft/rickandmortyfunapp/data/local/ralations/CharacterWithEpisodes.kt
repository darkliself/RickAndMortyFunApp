package alexlissoft.rickandmortyfunapp.data.local.ralations

import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterWithEpisodes(
    @Embedded val characterId: CharacterEntity,
    @Relation(
        parentColumn = "character_id",
        entityColumn = "episode_id",
        associateBy = Junction(CharacterEpisodeCrossRef::class)
    )
    val episodeId: List<EpisodeEntity>
)