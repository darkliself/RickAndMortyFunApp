package alexlissoft.rickandmortyfunapp.data.repository

import alexlissoft.rickandmortyfunapp.data.local.RoomDataBase
import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.paging.location.LocationsRemoteMediator
import alexlissoft.rickandmortyfunapp.data.paging.location.LocationPagingSource
import alexlissoft.rickandmortyfunapp.data.remote.api.RetrofitApiInterface
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestRepo @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    private val locationPagingSource: LocationPagingSource,
    private val unsplashApi: RetrofitApiInterface,
    private val unsplashDatabase: RoomDataBase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getAllImages(): Flow<PagingData<LocationEntity>> {
        val pagingSourceFactory = { unsplashDatabase.locationDao.getAllLocations() }

        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = LocationsRemoteMediator(
                roomDb = unsplashDatabase,
                retrofitApi = unsplashApi,
            ),
            pagingSourceFactory = {
                locationPagingSource
            }
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getAllImages2(): Flow<PagingData<LocationEntity>> {
        val pagingSourceFactory = { unsplashDatabase.locationDao.locationsPagingSource() }
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                pagingSourceFactory()
            }
        ).flow
    }

//    fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
//        return Pager(
//            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
//            pagingSourceFactory = {
//                SearchPagingSource(unsplashApi = unsplashApi, query = query)
//            }
//        ).flow
//    }
}