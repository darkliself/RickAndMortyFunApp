package alexlissoft.rickandmortyfunapp.data.repository

import alexlissoft.rickandmortyfunapp.data.local.RoomDataBase
import alexlissoft.rickandmortyfunapp.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class  TemporaryRepo  @Inject constructor(
    private val db: RoomDataBase
) {
     fun getCharactersById(ids: List<String>): Flow<List<CharacterEntity>> {
        return db.characterDao.getCharacterListById(ids)
    }

}