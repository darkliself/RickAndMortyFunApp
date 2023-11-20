package alexlissoft.rickandmortyfunapp.ui.theme.screens.location

import alexlissoft.rickandmortyfunapp.data.local.entity.LocationEntity
import alexlissoft.rickandmortyfunapp.data.repository.TestRepo
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MediatorViewModel @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    private val pager: Pager<Int, LocationEntity>,
    private val testRepo: TestRepo
) : ViewModel() {

    val locationPagingFlow2 = testRepo.getAllImages2()
        .cachedIn(viewModelScope)

    val locationPagingFlow = pager.flow.cachedIn(viewModelScope)

    fun initTest(): Flow<PagingData<LocationEntity>> {

        return testRepo.getAllImages()
    }

}

