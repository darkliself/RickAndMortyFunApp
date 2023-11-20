package alexlissoft.rickandmortyfunapp.di

import alexlissoft.rickandmortyfunapp.data.remote.api.RetrofitApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyApiModel {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): RetrofitApiInterface {
        return builder.build().create(RetrofitApiInterface::class.java)
    }
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
}
