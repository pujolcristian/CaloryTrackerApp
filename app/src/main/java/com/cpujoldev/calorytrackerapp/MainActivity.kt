package com.cpujoldev.calorytrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cpujoldev.calorytrackerapp.navigation.navigate
import com.cpujoldev.calorytrackerapp.ui.theme.CaloryTrackerAppTheme
import com.cpujoldev.core.navigation.Route
import com.cpujoldev.onboarding_presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Route.WELCOME_SCREEN) {
                    composable(Route.WELCOME_SCREEN) {
                        WelcomeScreen(onNavigate = navController::navigate)
                    }

                    composable(Route.AGE_SCREEN) {
                        // TODO: Implement
                    }

                    composable(Route.GENDER_SCREEN) {
                        // TODO: Implement
                    }

                    composable(Route.HEIGHT_SCREEN) {
                        // TODO: Implement
                    }

                    composable(Route.WEIGHT_SCREEN) {
                        // TODO: Implement
                    }

                    composable(Route.NUTRIENT_GOAL_SCREEN) {
                        // TODO: Implement
                    }

                    composable(Route.ACTIVITY_SCREEN) {
                        // TODO: Implement
                    }

                    composable(Route.GOAL_SCREEN) {
                        // TODO: Implement
                    }

                    composable(Route.TRACKER_OVERVIEW_SCREEN) {
                        // TODO: Implement
                    }

                    composable(Route.SEARCH_SCREEN) {
                        // TODO: Implement
                    }
                }
            }
        }
    }
}
