package dev.hinaka.pokedex.data.remote.responses.pokemon

import dev.hinaka.pokedex.data.remote.responses.common.NameAndUrl
import dev.hinaka.pokedex.domain.models.common.Sprites
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