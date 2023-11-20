package alexlissoft.rickandmortyfunapp.data.paging.episode

import alexlissoft.rickandmortyfunapp.data.local.RoomDataBase
import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import alexlissoft.rickandmortyfunapp.data.mappers.toEpisodeEntity
import alexlissoft.rickandmortyfunapp.data.remote.api.RetrofitApiInterface
import alexlissoft.rickandmortyfunapp.data.remote.episodes.Episodes
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ExperimentalPagingApi
class EpisodesRemoteMediator @Inject constructor(
    private val roomDb: RoomDataBase,
    private val  retrofitApi: RetrofitApiInterface,
    ) : RemoteMediator<Int, EpisodeEntity>() {
    private var pageIndex = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeEntity>
    ): MediatorResult {
        pageIndex = getPageIndex(loadType) ?:
                return MediatorResult.Success(endOfPaginationReached = true)
//        var limit = state.config.pageSize
        return try {
            withContext(Dispatchers.IO) {
                val launches = fetchLaunches(pageIndex)
                if (loadType == LoadType.REFRESH) {
                    withContext(Dispatchers.IO) {
                        roomDb.episodeDao.insertListOfEpisode(launches.listOfEpisodes.map { it.toEpisodeEntity() })
                    }
                } else {
                    withContext(Dispatchers.IO) {
                        roomDb.episodeDao.insertListOfEpisode(launches.listOfEpisodes.map { it.toEpisodeEntity() })
                    }
                }
                MediatorResult.Success(
                    endOfPaginationReached = launches.listOfEpisodes.isEmpty()
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
    ): Episodes {
        Log.d("test", "lets see whats is happening here")
        return retrofitApi.getEpisodesList(page)
    }

//    @AssistedFactory
//    interface Factory {
//        fun create(year: Int?): LaunchesRemoteMediator
//    }

}
