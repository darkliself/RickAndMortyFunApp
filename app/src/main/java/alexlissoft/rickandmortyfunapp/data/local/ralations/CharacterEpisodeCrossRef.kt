package alexlissoft.rickandmortyfunapp.data.local.ralations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["character_id", "episode_id"])
data class CharacterEpisodeCrossRef(
    @ColumnInfo(name = "character_id")
    val characterId: Int,
    @ColumnInfo(name = "episode_id")
    val episodeId: Int
)
