package dev.hinaka.pokedex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
  @PrimaryKey val id: Long,
  val name: String,
  val type1: Int,
  val type2: Int,
  val imageUrl: String,
)
