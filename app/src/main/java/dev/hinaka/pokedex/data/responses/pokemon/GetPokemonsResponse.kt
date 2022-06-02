package dev.hinaka.pokedex.data.responses.pokemon

import dev.hinaka.pokedex.domain.models.common.Id
import dev.hinaka.pokedex.domain.models.common.Sprites
import dev.hinaka.pokedex.domain.models.pokemon.Pokemon
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

fun GetPokemonsResponse.toPokemons() = results.orEmpty().map {
  Pokemon(
    id = Id(0),
    name = it.name.orEmpty(),
    types = emptyList(),
    sprites = Sprites(""),
  )
}