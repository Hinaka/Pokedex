package dev.hinaka.pokedex.ui.pokedex

import androidx.compose.runtime.Composable

@Composable
fun PokedexRoute(viewModel: PokemonListViewModel) {
  PokemonListScreen(viewModel)
}