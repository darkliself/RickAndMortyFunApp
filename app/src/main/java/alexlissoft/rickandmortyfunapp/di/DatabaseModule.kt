package alexlissoft.rickandmortyfunapp.di

import alexlissoft.rickandmortyfunapp.data.local.RoomDataBase
import alexlissoft.rickandmortyfunapp.data.local.dao.CharacterDao
import alexlissoft.rickandmortyfunapp.data.local.dao.EpisodeDao
import alexlissoft.rickandmortyfunapp.data.local.dao.LocationDao
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): RoomDataBase {
        return Room.databaseBuilder(
            appContext,
            RoomDataBase::class.java,
            "RickAndMortyDB"
        ).build()
    }

    @Provides
    fun providePlantDao(appDatabase: RoomDataBase): CharacterDao {
        return appDatabase.characterDao
    }

    @Provides
    fun providePlantDao2(appDatabase: RoomDataBase): EpisodeDao {
        return appDatabase.episodeDao
    }

    @Provides
    fun providePlantDao3(appDatabase: RoomDataBase): LocationDao {
        return appDatabase.locationDao
    }

}