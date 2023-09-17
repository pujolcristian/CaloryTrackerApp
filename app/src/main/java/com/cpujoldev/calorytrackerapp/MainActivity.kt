package com.cpujoldev.calorytrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cpujoldev.calorytrackerapp.navigation.navigate
import com.cpujoldev.calorytrackerapp.ui.theme.CaloryTrackerAppTheme
import com.cpujoldev.core.navigation.Route
import com.cpujoldev.onboarding_presentation.age.AgeScreen
import com.cpujoldev.onboarding_presentation.gender.GenderScreen
import com.cpujoldev.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerAppTheme {
                val snackBarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackBarHostState)
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME_SCREEN,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(Route.WELCOME_SCREEN) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }

                        composable(Route.GENDER_SCREEN) {
                            GenderScreen(onNavigate = navController::navigate)
                        }

                        composable(Route.AGE_SCREEN) {
                            AgeScreen(
                                snackBarHostState = snackBarHostState,
                                onNavigate = navController::navigate
                            )
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
}
