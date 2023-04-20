package com.ajmir.dailybugle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ajmir.ui.article.ArticleScreen
import com.ajmir.ui.common.resources.DailyBugleTheme
import com.ajmir.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyBugleTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = HOME_ROUTE) {
                    composable(HOME_ROUTE) {
                        HomeScreen(
                            onArticleClicked = { id ->
                                navController.navigate("$ARTICLE_PREFIX_ROUTE/$id")
                            }
                        )
                    }
                    composable(
                        route = ARTICLE_ROUTE,
                        arguments = listOf(
                            navArgument(ARTICLE_ID_ARG) {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getString(ARTICLE_ID_ARG)!!
                        Log.e("test", "onCreate: $id", )
                        ArticleScreen(
                            id = id,
                            onBack = { navController.popBackStack() }
                        )
                    }

                }
            }
        }
    }

    companion object {
        const val HOME_ROUTE = "home"
        const val ARTICLE_ID_ARG = "id"
        const val ARTICLE_PREFIX_ROUTE = "article"
        const val ARTICLE_ROUTE = "$ARTICLE_PREFIX_ROUTE/{id}"
    }
}
