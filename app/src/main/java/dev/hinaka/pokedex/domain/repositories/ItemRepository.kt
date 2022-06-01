package dev.hinaka.pokedex.domain.repositories

import dev.hinaka.pokedex.domain.models.item.Item

interface ItemRepository {
  suspend fun getItems(): List<Item>
}