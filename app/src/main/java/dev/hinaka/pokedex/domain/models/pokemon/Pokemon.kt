package dev.hinaka.pokedex.domain.models.pokemon

import dev.hinaka.pokedex.domain.models.common.Id
import dev.hinaka.pokedex.domain.models.common.Sprites
import dev.hinaka.pokedex.domain.models.type.Type

data class Pokemon(
  val id: Id,
  val name: String,
  val types: List<Type>,
  val sprites: Sprites,
)