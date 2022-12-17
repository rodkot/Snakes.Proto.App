package ru.nsu.ccfit.game.message

import ru.nsu.ccfit.game.data.model.GameConfig
import ru.nsu.ccfit.game.data.model.StateGame
import ru.nsu.ccfit.game.data.model.User
import java.net.DatagramSocket
import java.net.MulticastSocket

class MessageManager(
    socketUDP: DatagramSocket,
    socketMultiCast: MulticastSocket
) {

    suspend fun sendAnnouncementMsg(stateGame: StateGame, config: GameConfig) {

    }
}