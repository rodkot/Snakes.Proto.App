package ru.nsu.ccfit.game.message

import me.ippolitov.fit.snakes.SnakesProto.GameMessage

interface MessageHandler {
    fun handleMessage(message: GameMessage,senderAddress: String, senderPort: String) {

    }
}