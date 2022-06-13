package dev.hinaka.pokedex.features.pokemon.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.hinaka.pokedex.R
import dev.hinaka.pokedex.domain.models.common.Id
import dev.hinaka.pokedex.domain.models.common.Sprites
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import dev.hinaka.pokedex.domain.models.type.Type
import dev.hinaka.pokedex.domain.models.type.Type.BUG
import dev.hinaka.pokedex.domain.models.type.Type.DARK
import dev.hinaka.pokedex.domain.models.type.Type.DRAGON
import dev.hinaka.pokedex.domain.models.type.Type.ELECTRIC
import dev.hinaka.pokedex.domain.models.type.Type.FAIRY
import dev.hinaka.pokedex.domain.models.type.Type.FIGHTING
import dev.hinaka.pokedex.domain.models.type.Type.FIRE
import dev.hinaka.pokedex.domain.models.type.Type.FLYING
import dev.hinaka.pokedex.domain.models.type.Type.GHOST
import dev.hinaka.pokedex.domain.models.type.Type.GRASS
import dev.hinaka.pokedex.domain.models.type.Type.GROUND
import dev.hinaka.pokedex.domain.models.type.Type.ICE
import dev.hinaka.pokedex.domain.models.type.Type.NORMAL
import dev.hinaka.pokedex.domain.models.type.Type.POISON
import dev.hinaka.pokedex.domain.models.type.Type.PSYCHIC
import dev.hinaka.pokedex.domain.models.type.Type.ROCK
import dev.hinaka.pokedex.domain.models.type.Type.STEEL
import dev.hinaka.pokedex.domain.models.type.Type.UNKNOWN
import dev.hinaka.pokedex.domain.models.type.Type.WATER
import dev.hinaka.pokedex.ui.pokedex.PokemonListViewModel
import dev.hinaka.pokedex.ui.theme.ofType

@Composable
fun PokemonListScreen(
  viewModel: PokemonListViewModel,
) {
  val pokemons by viewModel.pokemons.collectAsState()
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(all = 8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    items(pokemons) { pokemon ->
      PokemonItem(pokemon = pokemon, modifier = Modifier.fillMaxWidth())
    }
  }
}

@Preview
@Composable
private fun PokemonItemPreview() {
  PokemonItem(pokemon = Pokemon(
    id = Id(1),
    name = "bulbasaur",
    types = listOf(GRASS, POISON),
    sprites = Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
  ))
}

@Composable
private fun PokemonItem(
  pokemon: Pokemon,
  modifier: Modifier = Modifier,
) {
  val surfaceColor = MaterialTheme.colors.ofType(pokemon.types.first()).copy(alpha = 0.7f)
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(8.dp),
    color = surfaceColor,
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp)
        .height(IntrinsicSize.Min),
      horizontalArrangement = Arrangement.Start,
      verticalAlignment = Alignment.CenterVertically
    ) {
      PokemonInfo(
        pokemon = pokemon,
        modifier = Modifier
          .weight(1f)
          .padding(vertical = 8.dp),
      )
      Spacer(modifier = Modifier.width(8.dp))
      PokemonImage(
        imageUrl = pokemon.sprites.default,
        modifier = Modifier.fillMaxHeight(),
      )
    }
  }
}

@Composable
private fun PokemonInfo(
  pokemon: Pokemon,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = "#${pokemon.id.toString().padStart(3, '0')}",
        style = TextStyle(
          color = Color.White,
          fontSize = 12.sp,
        ),
      )
      Spacer(Modifier.width(8.dp))
      Text(
        text = pokemon.name.replaceFirstChar { it.uppercase() },
        modifier = Modifier.weight(1f),
        style = TextStyle(
          color = Color.White,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
        ),
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      pokemon.types.map {
        PokemonType(it)
      }
    }
  }
}

@Composable
private fun PokemonType(
  type: Type,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier.widthIn(min = 64.dp),
    shape = RoundedCornerShape(16.dp),
    color = MaterialTheme.colors.ofType(type),
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
    ) {
      Image(
        painter = type.painter,
        contentDescription = "icon of ${type.displayName} type",
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = type.displayName.uppercase(),
        textAlign = TextAlign.Center,
        style = TextStyle(
          color = Color.White,
          fontSize = 12.sp,
        )
      )
    }
  }
}

@Composable
private fun PokemonImage(
  imageUrl: String,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(
      topStartPercent = 50,
      bottomStartPercent = 50,
    ),
    color = Color.White.copy(0.4f)
  ) {
    AsyncImage(
      model = imageUrl,
      contentDescription = null,
      modifier = Modifier
        .defaultMinSize(minHeight = 80.dp)
        .padding(start = 16.dp)
    )
  }
}

private val Type.painter: Painter
  @Composable get() = when (this) {
    NORMAL -> painterResource(id = R.drawable.ic_type_normal)
    FIGHTING -> painterResource(id = R.drawable.ic_type_fighting)
    FLYING -> painterResource(id = R.drawable.ic_type_flying)
    POISON -> painterResource(id = R.drawable.ic_type_poison)
    GROUND -> painterResource(id = R.drawable.ic_type_ground)
    ROCK -> painterResource(id = R.drawable.ic_type_rock)
    BUG -> painterResource(id = R.drawable.ic_type_bug)
    GHOST -> painterResource(id = R.drawable.ic_type_ghost)
    STEEL -> painterResource(id = R.drawable.ic_type_steel)
    FIRE -> painterResource(id = R.drawable.ic_type_fire)
    WATER -> painterResource(id = R.drawable.ic_type_water)
    GRASS -> painterResource(id = R.drawable.ic_type_grass)
    ELECTRIC -> painterResource(id = R.drawable.ic_type_electric)
    PSYCHIC -> painterResource(id = R.drawable.ic_type_psychic)
    ICE -> painterResource(id = R.drawable.ic_type_ice)
    DRAGON -> painterResource(id = R.drawable.ic_type_dragon)
    DARK -> painterResource(id = R.drawable.ic_type_dark)
    FAIRY -> painterResource(id = R.drawable.ic_type_fairy)
    UNKNOWN -> painterResource(id = R.drawable.ic_type_normal)
  }

private val Type.displayName: String
  @Composable get() = when (this) {
    NORMAL -> stringResource(id = R.string.type_normal)
    FIGHTING -> stringResource(id = R.string.type_fighting)
    FLYING -> stringResource(id = R.string.type_flying)
    POISON -> stringResource(id = R.string.type_poison)
    GROUND -> stringResource(id = R.string.type_ground)
    ROCK -> stringResource(id = R.string.type_rock)
    BUG -> stringResource(id = R.string.type_bug)
    GHOST -> stringResource(id = R.string.type_ghost)
    STEEL -> stringResource(id = R.string.type_steel)
    FIRE -> stringResource(id = R.string.type_fire)
    WATER -> stringResource(id = R.string.type_water)
    GRASS -> stringResource(id = R.string.type_grass)
    ELECTRIC -> stringResource(id = R.string.type_electric)
    PSYCHIC -> stringResource(id = R.string.type_psychic)
    ICE -> stringResource(id = R.string.type_ice)
    DRAGON -> stringResource(id = R.string.type_dragon)
    DARK -> stringResource(id = R.string.type_dark)
    FAIRY -> stringResource(id = R.string.type_fairy)
    UNKNOWN -> stringResource(id = R.string.type_unknown)
  }