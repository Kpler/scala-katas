package com.kpler.scala.kata.monocle.model.shared

final case class EntityWithQuantity(
  id: Int,
  name: String,
  confidence: Float,
  quantities: List[Quantity],
)
