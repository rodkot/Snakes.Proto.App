package ru.nsu.ccfit.game.data.model
data class State(
    val stateOrder: Int,
    val foods: List<Food>,
    val users: List<User>
)