package ru.nsu.ccfit.game.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.nsu.ccfit.game.data.model.GameConfig
import ru.nsu.ccfit.game.data.model.State
import ru.nsu.ccfit.game.data.model.StateGame
import ru.nsu.ccfit.game.view.GameEngine
import ru.nsu.ccfit.ui.theme.DarkGreen
import ru.nsu.ccfit.ui.theme.border2dp
import ru.nsu.ccfit.ui.theme.corner4dp
import ru.nsu.ccfit.ui.theme.padding16dp


@Composable
fun Board(state: StateGame, config: GameConfig) {
    BoxWithConstraints(Modifier.padding(padding16dp)) {
        val tileSize = maxWidth / config.weight;
        Box(
            Modifier
                .size(maxWidth)
                .border(border2dp, DarkGreen)
        )
        state.foods.forEach {
            Box(
                Modifier
                    .offset(x = tileSize * it.coord.x, y = tileSize * it.coord.y)
                    .size(tileSize)
                    .background(
                        DarkGreen
                    )
            )
        }
        state.users.forEach {
            it.snake.list.forEach { coord ->
                Box(
                    modifier = Modifier
                        .offset(x = tileSize * coord.x, y = tileSize * coord.y)
                        .size(tileSize)
                        .background(
                            DarkGreen, RoundedCornerShape(corner4dp)
                        )
                )
            }
        }
        state.owner.snake.list.forEach {
            Box(
                modifier = Modifier
                    .offset(x = tileSize * it.x, y = tileSize * it.y)
                    .size(tileSize)
                    .background(
                        DarkGreen, RoundedCornerShape(corner4dp)
                    )
            )
        }
    }
}