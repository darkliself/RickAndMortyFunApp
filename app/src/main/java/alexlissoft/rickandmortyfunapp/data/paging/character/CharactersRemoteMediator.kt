package alexlissoft.rickandmortyfunapp.data.paging.character

import alexlissoft.rickandmortyfunapp.data.local.RoomDataBase
import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import alexlissoft.rickandmortyfunapp.data.mappers.toCharacterEntity
import alexlissoft.rickandmortyfunapp.data.mappers.toEpisodeEntity
import alexlissoft.rickandmortyfunapp.data.remote.api.RetrofitApiInterface
import alexlissoft.rickandmortyfunapp.data.remote.characters.Characters
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
class CharactersRemoteMediator @Inject constructor(
    private val roomDb: RoomDataBase,
    private val  retrofitApi: RetrofitApiInterface,
    ) : RemoteMediator<Int, CharacterEntity>() {
    private var pageIndex = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        pageIndex = getPageIndex(loadType) ?:
                return MediatorResult.Success(endOfPaginationReached = true)
        return try {
            withContext(Dispatchers.IO) {
                val launches = fetchLaunches(pageIndex)
                if (loadType == LoadType.REFRESH) {
                    withContext(Dispatchers.IO) {
                        roomDb.characterDao.insertListOfCharacters(launches.listOfCharacters.map { it.toCharacterEntity() })
                    }
                } else {
                    withContext(Dispatchers.IO) {
                        roomDb.characterDao.insertListOfCharacters(launches.listOfCharacters.map { it.toCharacterEntity() })
                    }
                }
                MediatorResult.Success(
                    endOfPaginationReached = launches.listOfCharacters.isEmpty()
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
    ): Characters {
        Log.d("test", "lets see whats is happening here")
        return retrofitApi.getCharactersList(page)
    }
}
