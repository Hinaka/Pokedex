package dev.hinaka.pokedex.domain.repositories

import dev.hinaka.pokedex.domain.models.ability.Ability

interface AbilityRepository {
  suspend fun getAbilities(): List<Ability>
}