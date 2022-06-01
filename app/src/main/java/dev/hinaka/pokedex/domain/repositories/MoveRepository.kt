package dev.hinaka.pokedex.domain.repositories

import dev.hinaka.pokedex.domain.models.move.Move

interface MoveRepository {
  suspend fun getMoves(): List<Move>
}