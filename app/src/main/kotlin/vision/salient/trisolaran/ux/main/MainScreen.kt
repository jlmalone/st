package vision.salient.trisolaran.ux.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import vision.salient.trisolaran.ui.navigation.HandleNavBarNavigation
import vision.salient.trisolaran.ux.main.MainViewModel
import vision.salient.trisolaran.util.ext.requireActivity
import vision.salient.trisolaran.ux.NavGraph

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(vision.salient.trisolaran.util.ext.requireActivity()) // make sure we share the same ViewModel here and in MainAppScaffoldWithNavBar
) {
    val navController = rememberNavController()

    NavGraph(navController)

    HandleNavBarNavigation(viewModel, navController)
}
