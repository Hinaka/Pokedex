package dev.hinaka.pokedex.domain.models.move

import dev.hinaka.pokedex.domain.models.common.Id

data class Move(
  val id: Id,
  val name: String,
)
