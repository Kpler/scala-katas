package com.kpler.scala.kata.monocle.model.input

import com.kpler.scala.kata.monocle.model.shared.EntityWithQuantity

case class Product(description: String, kplerProduct: Option[EntityWithQuantity])
