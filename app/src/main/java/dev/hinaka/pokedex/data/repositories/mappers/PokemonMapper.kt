package dev.hinaka.pokedex.data.repositories.mappers

import dev.hinaka.pokedex.data.local.entities.PokemonEntity
import dev.hinaka.pokedex.data.remote.responses.pokemon.GetPokemonResponse
import dev.hinaka.pokedex.domain.models.type.Type

fun GetPokemonResponse.toEntity() = PokemonEntity(
  id = id?.toLong() ?: 0,
  name = name.orEmpty(),
  type1 = types?.firstOrNull()?.let {
    when (it.type?.name) {
      "normal" -> Type.NORMAL
      "fighting" -> Type.FIGHTING
      "flying" -> Type.FLYING
      "poison" -> Type.POISON
      "ground" -> Type.GROUND
      "rock" -> Type.ROCK
      "bug" -> Type.BUG
      "ghost" -> Type.GHOST
      "steel" -> Type.STEEL
      "fire" -> Type.FIRE
      "water" -> Type.WATER
      "grass" -> Type.GRASS
      "electric" -> Type.ELECTRIC
      "psychic" -> Type.PSYCHIC
      "ice" -> Type.ICE
      "dragon" -> Type.DRAGON
      "dark" -> Type.DARK
      "fairy" -> Type.FAIRY
      "unknown" -> Type.UNKNOWN
      else -> null
    }
  },
  type2 = types?.getOrNull(1)?.let {
    when (it.type?.name) {
      "normal" -> Type.NORMAL
      "fighting" -> Type.FIGHTING
      "flying" -> Type.FLYING
      "poison" -> Type.POISON
      "ground" -> Type.GROUND
      "rock" -> Type.ROCK
      "bug" -> Type.BUG
      "ghost" -> Type.GHOST
      "steel" -> Type.STEEL
      "fire" -> Type.FIRE
      "water" -> Type.WATER
      "grass" -> Type.GRASS
      "electric" -> Type.ELECTRIC
      "psychic" -> Type.PSYCHIC
      "ice" -> Type.ICE
      "dragon" -> Type.DRAGON
      "dark" -> Type.DARK
      "fairy" -> Type.FAIRY
      "unknown" -> Type.UNKNOWN
      else -> null
    }
  },
  imageUrl = sprites?.other?.officialArtwork?.front_default.orEmpty(),
)