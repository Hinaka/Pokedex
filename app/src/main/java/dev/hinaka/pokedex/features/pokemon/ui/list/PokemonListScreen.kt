package dev.hinaka.pokedex.features.pokemon.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import dev.hinaka.pokedex.domain.models.type.Type
import dev.hinaka.pokedex.ui.pokedex.PokemonListViewModel

@Composable
fun PokemonListScreen(
  viewModel: PokemonListViewModel,
) {
  val pokemons by viewModel.pokemons.collectAsState()
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
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
private fun PokemonItem(pokemon: Pokemon) {
  val surfaceModifier = Modifier
    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    .fillMaxWidth()

  Surface(
    modifier = surfaceModifier,
    shape = MaterialTheme.shapes.medium
  ) {
    Row(
      horizontalArrangement = Arrangement.Start,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      PokemonInfo(pokemon = pokemon, Modifier.weight(1f))
      Spacer(modifier = Modifier.width(8.dp))
      PokemonImage(pokemon = pokemon, Modifier.size(80.dp))
    }
  }
}

@Composable
private fun PokemonInfo(pokemon: Pokemon, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Row(modifier = Modifier.fillMaxWidth()) {
      Text(text = "#${pokemon.id}")
      Spacer(Modifier.width(16.dp))
      Text(
        text = pokemon.name.replaceFirstChar { it.uppercase() },
        modifier = Modifier.weight(1f)
      )
    }
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      pokemon.types.map {
        PokemonType(it, Modifier.weight(1f))
      }
    }
  }
}

@Composable
private fun PokemonType(type: Type, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(8.dp),
    border = BorderStroke(1.dp, Color.Black),
  ) {
    Text(
      text = type.name.uppercase(),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    )
  }
}

@Composable
private fun PokemonImage(pokemon: Pokemon, modifier: Modifier = Modifier) {
  Surface(
    shape = RoundedCornerShape(
      topStartPercent = 50,
      bottomStartPercent = 50,
    ),
    modifier = modifier,
    color = MaterialTheme.colors.primaryVariant,
  ) {
    AsyncImage(
      model = pokemon.sprites.default,
      contentDescription = null,
      modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    )
  }
}