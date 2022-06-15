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
  val type1: Int,
  val type2: Int,
  val imageUrl: String,
)

fun Pokemon.toEntity() = PokemonEntity(
  id = id.id,
  name = name,
  type1 = types.firstOrNull()?.ordinal ?: -1,
  type2 = types.lastOrNull()?.ordinal ?: -1,
  imageUrl = sprites.default,
)

fun PokemonEntity.toPokemon() = Pokemon(
  id = Id(id),
  name = name,
  types = mutableListOf<Type>().apply {
    Type.values().getOrNull(type1)?.also {
      add(it)
    }
    Type.values().getOrNull(type2)?.also {
      add(it)
    }
  },
  sprites = Sprites(imageUrl)
)