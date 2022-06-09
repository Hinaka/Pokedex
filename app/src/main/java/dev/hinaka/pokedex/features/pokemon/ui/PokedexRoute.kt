package dev.hinaka.pokedex.ui.pokedex

import androidx.compose.runtime.Composable
import dev.hinaka.pokedex.features.pokemon.ui.list.PokemonListScreen

@Composable
fun PokedexRoute(viewModel: PokemonListViewModel) {
  PokemonListScreen(viewModel)
}