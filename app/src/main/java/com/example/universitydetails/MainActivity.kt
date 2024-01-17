package com.example.universitydetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.universitydetails.ui.theme.UniversityDetailsTheme
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    private val universityViewModel: UniversityViewModel by lazy {
        ViewModelProvider(this).get(UniversityViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversityDetailsTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "universityList"
                ) {
                    composable("universityList") {
                        UniversityScreen(navController, universityViewModel)
                    }
                    composable(
                        route = "universityDetails/{universityName}",
                        arguments = listOf(navArgument("universityName") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val universityName = backStackEntry.arguments?.getString("universityName")
                        val universities = universityViewModel.universityData.value
                        val university = universities?.firstOrNull { it.name == universityName }
                        if (university != null) {
                            UniversityDetailsScreen(
                                university,
                                onBack = { navController.popBackStack() })
                        } else {
                            // Handle invalid universityName
                        }
                    }
                }
            }
        }
    }
}
