package dev.hinaka.pokedex.data.remote.responses.pokemon

import dev.hinaka.pokedex.data.remote.responses.common.NameAndUrl
import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonsResponse(
  val results: List<NameAndUrl>?,
)