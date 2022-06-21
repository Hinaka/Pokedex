package dev.hinaka.pokedex.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.hinaka.pokedex.domain.models.common.Id
import dev.hinaka.pokedex.domain.models.common.Sprites
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import dev.hinaka.pokedex.domain.models.type.Type

@Entity(tableName = "pokemons")
data class PokemonEntity(
  @PrimaryKey val id: Long,
  val name: String,
  val type1: Type?,
  val type2: Type?,
  val imageUrl: String,
)

fun Pokemon.toEntity() = PokemonEntity(
  id = id.id,
  name = name,
  type1 = types.firstOrNull(),
  type2 = types.lastOrNull(),
  imageUrl = sprites.default,
)

fun PokemonEntity.toPokemon() = Pokemon(
  id = Id(id),
  name = name,
  types = listOfNotNull(type1, type2),
  sprites = Sprites(imageUrl)
)