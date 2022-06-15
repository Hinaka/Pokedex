package dev.hinaka.pokedex.data.remote.responses.pokemon

import dev.hinaka.pokedex.data.remote.responses.common.NameAndUrl
import dev.hinaka.pokedex.domain.models.common.Id
import dev.hinaka.pokedex.domain.models.common.Sprites
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
import dev.hinaka.pokedex.domain.models.type.Type
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonResponse(
  val id: Int?,
  val name: String?,
  val types: List<PokemonType>?,
  val sprites: Sprites?,
) {

  @Serializable
  data class PokemonType(
    val slot: Int?,
    val type: NameAndUrl?,
  )

  @Serializable
  data class Sprites(
    val other: OtherSprites?,
  )

  @Serializable
  data class OtherSprites(
    @SerialName("official-artwork") val officialArtwork: OfficialArtworkSprites?,
  )

  @Serializable
  data class OfficialArtworkSprites(
    val front_default: String?,
  )
}

fun GetPokemonResponse.toPokemon() = id?.let {
  Pokemon(
    id = Id(it.toLong()),
    name = name.orEmpty(),
    sprites = Sprites(
      default = sprites?.other?.officialArtwork?.front_default.orEmpty(),
    ),
    types = types.orEmpty().mapNotNull { type ->
      when (type.type?.name) {
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
    }
  )
}