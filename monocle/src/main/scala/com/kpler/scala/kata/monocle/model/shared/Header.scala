package com.kpler.scala.kata.monocle.model.shared

import java.util.UUID

case class Header(
  extractedDate: String,
  sourceName: String,
  sourceFile: String,
  uuid: UUID,
  reportedDate: String,
  dataType: String,
)
