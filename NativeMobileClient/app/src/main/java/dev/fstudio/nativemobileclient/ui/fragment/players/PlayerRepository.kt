package dev.fstudio.nativemobileclient.ui.fragment.players

import dev.fstudio.nativemobileclient.api.MCWorldApi
import dev.fstudio.nativemobileclient.api.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.java.KoinJavaComponent.inject

class PlayerRepository {

    private val service by inject<MCWorldApi>(MCWorldApi::class.java)

    fun getTop(): Flow<List<Player>> {
        return  flow {
            val topPlayersList = service.getPlayersTop()
            emit(topPlayersList)
        }.flowOn(Dispatchers.IO)
    }
}