package alexlissoft.rickandmortyfunapp.ui.theme.screens.episodes

import alexlissoft.rickandmortyfunapp.data.local.entity.EpisodeEntity
import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.repository.TestRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class EpisodesViewModel
@Inject constructor(
    private val pager: Pager<Int, EpisodeEntity>,
//    private val locationMediator: LocationRemoteMediator,
    private val testRepo: TestRepo
) : ViewModel() {

    val episodesPagingFlow = pager.flow.cachedIn(viewModelScope)

    fun initTest(): Flow<PagingData<LocationEntity>> {
        return testRepo.getAllImages()
    }
}
