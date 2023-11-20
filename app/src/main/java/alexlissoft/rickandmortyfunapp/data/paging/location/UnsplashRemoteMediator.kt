package alexlissoft.rickandmortyfunapp.data.paging.location

import alexlissoft.rickandmortyfunapp.data.local.RoomDataBase
import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.mappers.toLocationEntity
import alexlissoft.rickandmortyfunapp.data.remote.api.RetrofitApiInterface
import alexlissoft.rickandmortyfunapp.data.remote.locatons.Locations
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ExperimentalPagingApi
class LocationsRemoteMediator @Inject constructor(
    private val roomDb: RoomDataBase,
    private val  retrofitApi: RetrofitApiInterface,
    ) : RemoteMediator<Int, LocationEntity>() {
    private var pageIndex = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationEntity>
    ): MediatorResult {
        pageIndex = getPageIndex(loadType) ?:
                return MediatorResult.Success(endOfPaginationReached = true)
//        var limit = state.config.pageSize
        return try {
            withContext(Dispatchers.IO) {
                val launches = fetchLaunches(pageIndex)
                if (loadType == LoadType.REFRESH) {
                    withContext(Dispatchers.IO) {
                        roomDb.locationDao.insertListOfLocations(launches.locationsList.map { it.toLocationEntity() })
                    }
                } else {
                    withContext(Dispatchers.IO) {
                        roomDb.locationDao.insertListOfLocations(launches.locationsList.map { it.toLocationEntity() })
                    }
                }
                MediatorResult.Success(
                    endOfPaginationReached = launches.locationsList.isEmpty()
                )
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    private suspend fun fetchLaunches(
        page: Int,
    ): Locations {
        Log.d("test", "lets see whats is happening here")
        return retrofitApi.getLocationsList(page)
    }

//    @AssistedFactory
//    interface Factory {
//        fun create(year: Int?): LaunchesRemoteMediator
//    }

}
