package br.fiap.spacefarm

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.fiap.spacefarm.models.AgroViewModel
import br.fiap.spacefarm.screens.DetalhesScreen
import br.fiap.spacefarm.screens.InicialScreen
import br.fiap.spacefarm.screens.MonitoramentoScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    val viewModel: AgroViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            InicialScreen(navController = navController)
        }
        composable("monitoramento") {
            MonitoramentoScreen(navController = navController, viewModel = viewModel)
        }
        composable("detalhes/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            DetalhesScreen(navController = navController, viewModel = viewModel, id = id)
        }
    }
}




    