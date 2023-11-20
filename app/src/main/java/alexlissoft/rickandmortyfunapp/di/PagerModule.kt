package alexlissoft.rickandmortyfunapp.di

import alexlissoft.rickandmortyfunapp.data.local.RoomDataBase
import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.paging.character.CharactersRemoteMediator
import alexlissoft.rickandmortyfunapp.data.paging.episode.EpisodesRemoteMediator
import alexlissoft.rickandmortyfunapp.data.paging.location.LocationsRemoteMediator
import alexlissoft.rickandmortyfunapp.data.remote.api.RetrofitApiInterface
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@InstallIn(SingletonComponent::class)
@Module
object PagerModule {
    @Provides
    @Singleton
    fun provideLocationPager(appDb: RoomDataBase, retrofitApi: RetrofitApiInterface): Pager<Int, LocationEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = LocationsRemoteMediator(
                roomDb = appDb,
                retrofitApi = retrofitApi,
            ),
            pagingSourceFactory = {
                appDb.locationDao.locationsPagingSource()
            }
        )
    }
    @Provides
    @Singleton
    fun provideCharactersPager(appDb: RoomDataBase, retrofitApi: RetrofitApiInterface): Pager<Int, CharacterEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharactersRemoteMediator(
                roomDb = appDb,
                retrofitApi = retrofitApi,
            ),
            pagingSourceFactory = {
                appDb.characterDao.charactersPagingSource()
            }
        )
    }
    @Provides
    @Singleton
    fun provideEpisodesPager(appDb: RoomDataBase, retrofitApi: RetrofitApiInterface): Pager<Int, EpisodeEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = EpisodesRemoteMediator(
                roomDb = appDb,
                retrofitApi = retrofitApi,
            ),
            pagingSourceFactory = {
                appDb.episodeDao.episodesPagingSource()
            }
        )
    }
}