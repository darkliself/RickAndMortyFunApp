package alexlissoft.rickandmortyfunapp.data.mappers

import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import alexlissoft.rickandmortyfunapp.data.remote.episodes.EpisodesInfo


fun EpisodesInfo.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        episodeId = id,
        name = name,
        airDate = airDate,
        created = created,
        episode = episode,
        url = url
    )
}

fun EpisodeEntity.toEpisodesInfo(): EpisodesInfo {
    return EpisodesInfo(
        id = episodeId,
        name = name,
        airDate = airDate,
        characters = emptyList(),
        created = created,
        episode = episode,
        url = url
    )
}