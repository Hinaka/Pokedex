package dev.hinaka.pokedex.ui

import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.hinaka.pokedex.ui.PokedexDestinations.ABILITY_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.ITEM_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.MOVE_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.NATURE_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.POKEDEX_ROUTE
import dev.hinaka.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.launch

@Composable
fun PokedexApp() {
  PokedexTheme {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = MaterialTheme.colors.primaryVariant
    SideEffect {
      systemUiController.setStatusBarColor(statusBarColor)
    }

    val coroutineScope = rememberCoroutineScope()

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: POKEDEX_ROUTE

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalDrawer(
      drawerContent = {
        AppDrawer(
          currentRoute = currentRoute,
          navigateToPokedex = {
            navController.navigate(POKEDEX_ROUTE)
          },
          navigateToMove = {
            navController.navigate(MOVE_ROUTE)
          },
          navigateToAbility = {
            navController.navigate(ABILITY_ROUTE)
          },
          navigateToItem = {
            navController.navigate(ITEM_ROUTE)
          },
          navigateToNature = {
            navController.navigate(NATURE_ROUTE)
          },
          closeDrawer = {
            coroutineScope.launch { drawerState.close() }
          }
        )
      },
      drawerState = drawerState,
    ) {
      PokedexNavGraph(
        navController = navController,
      )
    }
  }
}