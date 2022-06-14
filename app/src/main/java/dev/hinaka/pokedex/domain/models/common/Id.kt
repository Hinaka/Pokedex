package dev.hinaka.pokedex.domain.models.common

@JvmInline
value class Id(val id: Long) {
  override fun toString(): String {
    return id.toString()
  }
}