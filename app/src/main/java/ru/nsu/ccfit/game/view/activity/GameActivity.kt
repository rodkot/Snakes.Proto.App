package ru.nsu.ccfit.game.view.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.nsu.ccfit.R
import ru.nsu.ccfit.game.data.GameCache
import ru.nsu.ccfit.game.data.TOP_10
import ru.nsu.ccfit.game.data.model.*
import ru.nsu.ccfit.game.message.MessageManager
import ru.nsu.ccfit.game.view.GameEngine
import ru.nsu.ccfit.game.view.screen.EndScreen
import ru.nsu.ccfit.game.view.screen.GameScreen
import java.net.DatagramSocket
import java.net.MulticastSocket

class GameActivity : BaseActivity() {
    private lateinit var dataStore: GameCache
    private val isPlaying = mutableStateOf(true)
    private var score = mutableStateOf(0)
    private lateinit var scope: CoroutineScope
    private lateinit var playerName: String
    private lateinit var highScores: List<HighScore>

    private var gameEngine = GameEngine(
        scope = lifecycleScope,
        config = GameConfig(),
        stateGame   =
                StateGame(
                    foods = listOf(Food(Coord(4, 5))),
                    owner = User(
                        name = "dd",
                        snake = Snake(
                            list = listOf(Coord(2, 2)),
                            direction = Snake.Direction.DOWN
                        )
                    )
                ),
        manager = MessageManager(DatagramSocket(), MulticastSocket()),
        onGameEnded = {
            if (isPlaying.value) {
                isPlaying.value = false
                scope.launch { dataStore.saveHighScore(highScores) }
            }
        },
        onFoodEaten = { score.value++ }
    )

    @Composable
    override fun Content() {
        scope = rememberCoroutineScope()
        dataStore = GameCache(applicationContext)
        playerName =
            dataStore.getPlayerName.collectAsState(initial = stringResource(id = R.string.default_player_name)).value
        highScores = dataStore.getHighScores.collectAsState(initial = listOf()).value.plus(
            HighScore(playerName, score.value)
        ).sortedByDescending { it.score }.take(TOP_10)
        Column {
            if (isPlaying.value) {
                GameScreen(gameEngine, score.value)
            }
//            else {
//                EndScreen(score.value) {
//                    score.value = 0
//                    gameEngine.reset()
//                    isPlaying.value = true
//                }
//            }
        }
    }
}