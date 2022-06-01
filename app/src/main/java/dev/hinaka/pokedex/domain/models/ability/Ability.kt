package dev.hinaka.pokedex.domain.models.ability

import dev.hinaka.pokedex.domain.models.common.Id

data class Ability(
  val id: Id,
  val name: String,
)
