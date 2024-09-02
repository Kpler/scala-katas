package com.kpler.scala.kata.monocle.model.output

import java.util.UUID

final case class EntityEdit(id: Option[Int], editedBy: UUID, editedAt: String)
