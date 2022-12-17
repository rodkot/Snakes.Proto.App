package ru.nsu.ccfit.game.view.activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mukeshsolanki.snake.presentation.screen.AboutScreen
import ru.nsu.ccfit.game.view.navigation.Screen
import ru.nsu.ccfit.game.view.screen.HighScoreScreen
import ru.nsu.ccfit.game.view.screen.MenuScreen
import ru.nsu.ccfit.game.view.screen.SettingScreen

class MainActivity : BaseActivity() {
    private lateinit var navController: NavHostController

    @Composable
    override fun Content() {
        navController = rememberNavController()
        SetupNavigation()
    }

    @Composable
    private fun SetupNavigation() {
        NavHost(navController = navController, startDestination = Screen.Menu.route) {
            composable(Screen.Menu.route) { MenuScreen(navController) }
          //  composable(Screen.HighScores.route) { HighScoreScreen(navController) }
            composable(Screen.Settings.route) { SettingScreen(navController) }
           // composable(Screen.About.route) { AboutScreen(navController) }
        }
    }
}
