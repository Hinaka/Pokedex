package dev.hinaka.pokedex.domain.repositories

import dev.hinaka.pokedex.domain.models.nature.Nature

interface NatureRepository {
  suspend fun getNatures(): List<Nature>
}