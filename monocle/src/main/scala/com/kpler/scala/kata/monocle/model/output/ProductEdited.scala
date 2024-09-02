package com.kpler.scala.kata.monocle.model.output

import com.kpler.scala.kata.monocle.model.shared.Quantity

import java.util.UUID

final case class ProductEdited(
  id: Option[Int],
  quantities: List[Quantity],
  editedBy: UUID,
  editedAt: String,
)
