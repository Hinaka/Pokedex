package dev.hinaka.pokedex.domain.models

import dev.hinaka.pokedex.domain.models.common.Id
import dev.hinaka.pokedex.domain.models.common.Sprites

data class Pokemon(
  val id: Id,
  val name: String,
  val types: List<Type>,
  val sprites: Sprites,
)