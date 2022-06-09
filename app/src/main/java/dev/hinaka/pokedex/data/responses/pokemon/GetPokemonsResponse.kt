package dev.hinaka.pokedex.data.responses.pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonsResponse(
  val results: List<Item>?,
) {

  @Serializable
  data class Item(
    val name: String?,
    val url: String?,
  )
}