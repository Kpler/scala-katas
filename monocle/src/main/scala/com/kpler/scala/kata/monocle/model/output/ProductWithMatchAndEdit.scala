package com.kpler.scala.kata.monocle.model.output

import com.kpler.scala.kata.monocle.model.input.Product
import com.kpler.scala.kata.monocle.model.shared.EntityWithQuantity

final case class ProductWithMatchAndEdit(
  name: String,
  manualEdit: Option[ProductEdited],
  matcherResult: Option[EntityWithQuantity],
)

object ProductWithMatchAndEdit {
  def apply(product: Product): ProductWithMatchAndEdit =
    ProductWithMatchAndEdit(name = product.description, manualEdit = None, matcherResult = product.kplerProduct)

  def withMatchedValueButNoMatch(usedForMatching: String): ProductWithMatchAndEdit =
    ProductWithMatchAndEdit(usedForMatching, None, None)

  def withMatchedValueAndMatch(usedForMatching: String, matched: EntityWithQuantity): ProductWithMatchAndEdit =
    ProductWithMatchAndEdit(usedForMatching, None, Some(matched))
}
