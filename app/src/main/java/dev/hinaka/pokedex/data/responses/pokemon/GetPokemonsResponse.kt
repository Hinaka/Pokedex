package dev.hinaka.pokedex.data.responses.pokemon

import dev.hinaka.pokedex.data.responses.common.NameAndUrl
import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonsResponse(
  val results: List<NameAndUrl>?,
)