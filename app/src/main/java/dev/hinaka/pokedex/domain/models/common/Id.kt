package dev.hinaka.pokedex.domain.models.common

@JvmInline
value class Id(private val id: Long) {
  override fun toString(): String {
    return id.toString().padStart(3, '0')
  }
}