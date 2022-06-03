package dev.hinaka.pokedex.ui.pokedex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon

@Composable
fun PokemonListScreen(
  viewModel: PokemonListViewModel,
) {
  val pokemons by viewModel.pokemons.collectAsState()
  LazyColumn(
    modifier = Modifier
      .padding(horizontal = 16.dp)
      .fillMaxSize(),
  ) {
    items(
      items = pokemons,
      itemContent = {
        PokemonItem(pokemon = it)
      }
    )
  }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
  Text(text = pokemon.name)
}