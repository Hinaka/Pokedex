package dev.hinaka.pokedex.features.pokemon.ui

import androidx.compose.runtime.Composable
import dev.hinaka.pokedex.features.pokemon.ui.list.PokemonListScreen
import dev.hinaka.pokedex.features.pokemon.ui.list.PokemonListViewModel

@Composable
fun PokedexRoute(viewModel: PokemonListViewModel) {
  PokemonListScreen(viewModel)
}