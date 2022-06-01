package dev.hinaka.pokedex.domain.models.item

import dev.hinaka.pokedex.domain.models.common.Id

data class Item(
  val id: Id,
  val name: String,
)
