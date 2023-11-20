package alexlissoft.rickandmortyfunapp.data.mappers

import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.remote.locatons.LocationInfo


fun LocationInfo.toLocationEntity(): LocationEntity {
    return LocationEntity(
        locationId = id,
        name = name,
        dimension = dimension,
        created = created,
        type = type,
        url = url
    )
}

fun LocationEntity.toLocationInfo(): LocationInfo {
    return LocationInfo(
        id = locationId,
        name = name,
        dimension = dimension,
        residents = emptyList(),
        created = created,
        type = type,
        url = url
    )
}
