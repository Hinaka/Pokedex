package dev.hinaka.pokedex

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import dev.hinaka.pokedex.PokedexDestinations.ABILITY_ROUTE
import dev.hinaka.pokedex.PokedexDestinations.ITEM_ROUTE
import dev.hinaka.pokedex.PokedexDestinations.MOVE_ROUTE
import dev.hinaka.pokedex.PokedexDestinations.NATURE_ROUTE
import dev.hinaka.pokedex.PokedexDestinations.POKEDEX_ROUTE
import dev.hinaka.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.launch

@Composable
fun PokedexApp() {
  PokedexTheme {
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalDrawer(
      drawerContent = {
        AppDrawer(
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