package com.kpler.scala.kata.monocle.model.edit

import com.kpler.scala.kata.monocle.model.shared.Quantity

final case class ProductEdit(id: Option[Int], quantities: List[Quantity])
