package ru.nsu.ccfit.game.data.model

data class Snake(
    val playerId: Int = 0,
    val state: State = State.ALIVE,
    val list: List<Coord>,
    val direction: Direction
) {
    enum class State {
        ALIVE,
        ZOMBIE
    }

    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}