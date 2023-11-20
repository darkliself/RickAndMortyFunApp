package alexlissoft.rickandmortyfunapp.ui.theme.screens.characters

import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import alexlissoft.rickandmortyfunapp.data.repository.TemporaryRepo
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val pager: Pager<Int, CharacterEntity>,
    private val testRepo: TemporaryRepo
) : ViewModel() {

    val charactersPagingFlow = pager.flow.cachedIn(viewModelScope)
    fun loadCharactersById(id: List<String>): Flow<List<CharacterEntity>> {
        return testRepo.getCharactersById(id)
    }
}
