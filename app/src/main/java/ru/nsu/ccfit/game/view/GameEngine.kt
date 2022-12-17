package ru.nsu.ccfit.game.view

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.nsu.ccfit.game.data.model.*
import ru.nsu.ccfit.game.message.MessageManager
import ru.nsu.ccfit.game.view.SnakeDirection.Right
import java.net.InetAddress
import java.util.*
import kotlin.streams.toList

class GameEngine(
    private val scope: CoroutineScope,
    val config: GameConfig,
    val stateGame: StateGame,
    val manager: MessageManager,
    private val onGameEnded: () -> Unit,
    private val onFoodEaten: () -> Unit
) {
    private val mutex = Mutex()

    private val mutableState =
        MutableSharedFlow<StateGame>(
            replay = 10
        )

    val state: SharedFlow<StateGame> = mutableState
    private val currentDirection = mutableStateOf(Snake.Direction.RIGHT)

    var move = Pair(1, 0)
        set(value) {
            scope.launch {
                mutex.withLock {
                    field = value
                }
            }
        }

//    fun reset() {
//        mutableState.update {
//            it.copy(
//                food = Pair(5, 5),
//                snake = listOf(Pair(7, 7)),
//                currentDirection = SnakeDirection.Right
//            )
//        }
//        currentDirection.value = SnakeDirection.Right
//        move = Pair(1, 0)
//    }


    init {
        scope.launch {
            mutableState.emit(stateGame);



            var snakeLength = 2
            withContext(Dispatchers.IO) {

            }

            delay(150)
            mutableState.collect() { it ->
//                    val snake = it.owner.snake;
//                    val hasReachedLeftEnd =
//                        it.owner.snake.list.first().x == 0 && it.owner.snake.direction == Snake.Direction.LEFT
//                    val hasReachedTopEnd =
//                        it.snake.first().second == 0 && it.currentDirection == SnakeDirection.Up
//                    val hasReachedRightEnd =
//                        it.snake.first().first == BOARD_SIZE - 1 && it.currentDirection == SnakeDirection.Right
//                    val hasReachedBottomEnd =
//                        it.snake.first().second == BOARD_SIZE - 1 && it.currentDirection == SnakeDirection.Down
//                    if (hasReachedLeftEnd || hasReachedTopEnd || hasReachedRightEnd || hasReachedBottomEnd) {
//                        snakeLength = 2
//                        onGameEnded.invoke()
//                    }
                if (move.first == 0 && move.second == -1) {
                    currentDirection.value = Snake.Direction.UP
                } else if (move.first == -1 && move.second == 0) {
                    currentDirection.value = Snake.Direction.LEFT
                } else if (move.first == 1 && move.second == 0) {
                    currentDirection.value = Snake.Direction.RIGHT
                } else if (move.first == 0 && move.second == 1) {
                    currentDirection.value = Snake.Direction.DOWN
                }
                val position = it.owner.snake.list.first().let { pos ->
                    mutex.withLock {
                        Coord(
                            (pos.x + move.first + config.weight) % config.weight,
                            (pos.y + move.second + config.height) % config.height
                        )
                    }
                }
//                    val newPosition = it.owner.snake.list.let { poz ->
//                        mutex.withLock {
//                            Coord(
//                                (poz. + move.first + BOARD_SIZE) % BOARD_SIZE,
//                                (poz.second + move.second + BOARD_SIZE) % BOARD_SIZE
//                            )
//                        }
//                    }
                if (it.foods.stream().map { it.coord }.toList().contains(position)) {
                    onFoodEaten.invoke()
                    snakeLength++
                }

                if (it.owner.snake.list.contains(position)) {
                    snakeLength = 2
                    onGameEnded.invoke()
                }
//TODO Сделать копирование нормальное
                it.copy()
            }
        }
    }
}

//    companion object {
//        const val BOARD_SIZE = 100
//    }

