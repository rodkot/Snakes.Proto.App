package ru.nsu.ccfit.game.data.model

data class StateGame(
    val stateOrder: Int = 0,
    val owner: User,
    val foods: List<Food> = ArrayList<Food>(),
    val users: List<User> = ArrayList<User>()
) {
}