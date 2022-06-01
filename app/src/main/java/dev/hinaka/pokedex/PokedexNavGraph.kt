package dev.hinaka.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.hinaka.pokedex.PokedexDestinations.ABILITY_ROUTE
import dev.hinaka.pokedex.PokedexDestinations.ITEM_ROUTE
import dev.hinaka.pokedex.PokedexDestinations.MOVE_ROUTE
import dev.hinaka.pokedex.PokedexDestinations.NATURE_ROUTE
import dev.hinaka.pokedex.PokedexDestinations.POKEDEX_ROUTE
import dev.hinaka.pokedex.ui.ability.AbilityRoute
import dev.hinaka.pokedex.ui.item.ItemRoute
import dev.hinaka.pokedex.ui.move.MoveRoute
import dev.hinaka.pokedex.ui.nature.NatureRoute
import dev.hinaka.pokedex.ui.pokedex.PokedexRoute

@Composable
fun PokedexNavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  startDestination: String = POKEDEX_ROUTE,
) {
  NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier,
  ) {
    composable(POKEDEX_ROUTE) {
      PokedexRoute()
    }
    composable(MOVE_ROUTE) {
      MoveRoute()
    }
    composable(ABILITY_ROUTE) {
      AbilityRoute()
    }
    composable(ITEM_ROUTE) {
      ItemRoute()
    }
    composable(NATURE_ROUTE) {
      NatureRoute()
    }
  }
}