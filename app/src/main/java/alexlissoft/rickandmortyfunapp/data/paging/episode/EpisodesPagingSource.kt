package alexlissoft.rickandmortyfunapp.data.paging.episode

import alexlissoft.rickandmortyfunapp.data.local.RoomDataBase
import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.remote.api.RetrofitApiInterface
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.ceil

class EpisodesPagingSource @Inject constructor(
    private val retrofitApi: RetrofitApiInterface,
    private val roomDb: RoomDataBase,
) : PagingSource<Int, EpisodeEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeEntity> {
        var currentPage = params.key ?: 1
        var totalPages: Int
        return try {
            withContext(Dispatchers.IO) {
                val ttt = roomDb.episodeDao.getAllEpisodesNotFlow()
                totalPages = ceil(ttt.size / 20.toFloat()).toInt()
                if (currentPage <= totalPages) {
                    withContext(Dispatchers.IO) {
                        LoadResult.Page(
                            data = ttt.drop((currentPage - 1) * 20).take(20),
                            prevKey = if (currentPage == 1) null else currentPage - 1,
                            nextKey = if (currentPage == totalPages) null else currentPage + 1
                        )
                    }
                } else {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, EpisodeEntity>): Int? {
        return state.anchorPosition
    }
}