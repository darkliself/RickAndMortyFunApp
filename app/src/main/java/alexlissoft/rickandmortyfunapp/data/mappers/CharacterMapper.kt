package alexlissoft.rickandmortyfunapp.data.mappers

import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.data.remote.characters.CharacterInfo
import alexlissoft.rickandmortyfunapp.data.remote.characters.Location
import alexlissoft.rickandmortyfunapp.data.remote.characters.Origin


fun CharacterInfo.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        characterId = id,
        name = name,
        created = created,
        gender = gender,
        image = image,
        locationName = location.name,
        location = location.url,
        origin = origin.url,
        originName = origin.name,
        species = species,
        status = status,
        type = type,
        url = url
    )
}

fun CharacterEntity.toCharacterInfo(): CharacterInfo {
    return CharacterInfo(
        id = characterId,
        name = name,
        created = created,
        episode = emptyList(),
        gender = gender,
        image = image,
        location = Location(
            name = locationName,
            url = location
        ),
        origin = Origin(
            name = originName,
            url = origin
        ),
        species = species,
        status = status,
        type = type,
        url = url
    )
}


