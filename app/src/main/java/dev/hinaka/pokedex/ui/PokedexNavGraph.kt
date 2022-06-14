package dev.hinaka.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.hinaka.pokedex.ui.PokedexDestinations.ABILITY_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.ITEM_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.MOVE_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.NATURE_ROUTE
import dev.hinaka.pokedex.ui.PokedexDestinations.POKEDEX_ROUTE
import dev.hinaka.pokedex.ui.ability.AbilityRoute
import dev.hinaka.pokedex.ui.item.ItemRoute
import dev.hinaka.pokedex.ui.move.MoveRoute
import dev.hinaka.pokedex.ui.nature.NatureRoute
import dev.hinaka.pokedex.ui.pokedex.PokedexRoute
import dev.hinaka.pokedex.features.pokemon.ui.list.PokemonListViewModel

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
      val viewModel = hiltViewModel<PokemonListViewModel>()
      PokedexRoute(viewModel)
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