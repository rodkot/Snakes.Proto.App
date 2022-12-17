package ru.nsu.ccfit.game.data.model

import java.net.InetAddress

data class User(
    val id: Int = 0,
    val name: String,
    val address: InetAddress? = null,
    val role: Role = Role.MASTER,
    val type: Type = Type.HUMAN,
    val scope: Int = 0,
    val snake: Snake
) {
    enum class Role {
        NORMAL,
        MASTER,
        DEPUTY,
        VIEWER
    }

    enum class Type {
        HUMAN,
        ROBOT
    }
}