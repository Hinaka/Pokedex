package dev.hinaka.pokedex.features.pokemon.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import dev.hinaka.pokedex.domain.models.type.Type
import dev.hinaka.pokedex.ui.pokedex.PokemonListViewModel
import kotlinx.coroutines.launch

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
        PokemonItem(pokemon = it, modifier = Modifier.fillMaxWidth())
      }
    )
  }
}

@Composable
private fun PokemonItem(pokemon: Pokemon, modifier: Modifier = Modifier) {
  var palette by remember { mutableStateOf<Palette?>(null) }

  val request = ImageRequest.Builder(LocalContext.current)
    .data(pokemon.sprites.default)
    .allowHardware(false)
    .size(Size.ORIGINAL)
    .build()

  val painter = rememberAsyncImagePainter(model = request)
  val state = painter.state
  if (state is AsyncImagePainter.State.Success) {
    val bitmap = state.result.drawable.toBitmap()
    LaunchedEffect(key1 = bitmap) {
      launch {
        Palette.Builder(bitmap).generate { palette = it }
      }
    }
  }

  val dominantSwatch = palette?.dominantSwatch
  val dominantColor = dominantSwatch?.rgb?.let { Color(it) } ?: Color.White
  val textColor = dominantSwatch?.bodyTextColor?.let { Color(it) } ?: Color.White

  val surfaceModifier = Modifier
    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    .fillMaxWidth()

  Surface(
    modifier = surfaceModifier,
    shape = RoundedCornerShape(8.dp),
    color = dominantColor,
  ) {
    Row(
      horizontalArrangement = Arrangement.Start,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp)
        .height(IntrinsicSize.Min)
    ) {
      PokemonInfo(pokemon = pokemon,
        Modifier
          .weight(1f)
          .padding(vertical = 8.dp), textColor)
      Spacer(modifier = Modifier.width(8.dp))
      PokemonImage(painter,
        Modifier
          .width(80.dp)
          .fillMaxHeight())
    }
  }
}

@Composable
private fun PokemonInfo(pokemon: Pokemon, modifier: Modifier = Modifier, textColor: Color) {
  Column(modifier = modifier) {
    Row(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = "#${pokemon.id}",
        color = textColor,
        style = MaterialTheme.typography.subtitle1,
      )
      Spacer(Modifier.width(16.dp))
      Text(
        text = pokemon.name.replaceFirstChar { it.uppercase() },
        modifier = Modifier.weight(1f),
        color = textColor,
        style = MaterialTheme.typography.subtitle1,
      )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      pokemon.types.map {
        PokemonType(it, Modifier.weight(1f), textColor)
      }
    }
  }
}

@Composable
private fun PokemonType(type: Type, modifier: Modifier = Modifier, textColor: Color) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(16.dp),
    border = BorderStroke(1.dp, textColor),
    color = Color.Transparent,
  ) {
    Text(
      text = type.name.uppercase(),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),
      color = textColor,
      style = MaterialTheme.typography.caption
    )
  }
}

@Composable
private fun PokemonImage(painter: AsyncImagePainter, modifier: Modifier = Modifier) {
  Surface(
    shape = RoundedCornerShape(
      topStartPercent = 50,
      bottomStartPercent = 50,
    ),
    color = Color.White.copy(0.4f),
    modifier = modifier
  ) {
    Image(painter = painter, contentDescription = null, modifier = Modifier.padding(4.dp))
  }
}