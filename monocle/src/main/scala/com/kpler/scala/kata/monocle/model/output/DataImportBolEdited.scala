package com.kpler.scala.kata.monocle.model.output

import com.kpler.scala.kata.monocle.model.edit.{ Edit, EditedEntity }
import com.kpler.scala.kata.monocle.model.input.DataImportBol
import io.github.arainko.ducktape.*

final case class DataImportBolEdited(
  masterBOLNumber: String,
  importDate: String,
  estimatedArrivalDate: String,
  verified: Boolean,
  visible: Boolean,
  vessel: Option[EntityWithMatchAndEdit],
  consignee: Option[EntityWithMatchAndEdit],
  shipper: Option[EntityWithMatchAndEdit],
  notifyParty: Option[EntityWithMatchAndEdit],
  origin: Option[EntityWithMatchAndEdit],
  destination: Option[EntityWithMatchAndEdit],
  products: List[ProductWithMatchAndEdit],
  raw: String,
) {
  def processOptionalEdit(
    entityWithMatchAndEdit: Option[EntityWithMatchAndEdit],
    editedEntity: Option[EditedEntity],
    edit: Edit,
  ): Option[EntityWithMatchAndEdit] =
    (entityWithMatchAndEdit, editedEntity) match {
      case (Some(entityWithMatchAndEdit), Some(editedEntity)) =>
        Some(entityWithMatchAndEdit.applyEdit(editedEntity, edit.userId, edit.timestamp))
      case (Some(entityWithMatchAndEdit), None) => Some(entityWithMatchAndEdit)
      case _ => None
    }

  def applyEdit(edit: Edit): DataImportBolEdited = {
    this.copy(
      verified = edit.verified.getOrElse(this.verified),
      visible = edit.visible.getOrElse(this.visible),
      vessel = processOptionalEdit(this.vessel, edit.vessel, edit),
      consignee = processOptionalEdit(this.consignee, edit.consignee, edit),
      shipper = processOptionalEdit(this.shipper, edit.shipper, edit),
      notifyParty = processOptionalEdit(this.notifyParty, edit.notificationParty, edit),
      origin = processOptionalEdit(this.origin, edit.origin, edit),
      destination = processOptionalEdit(this.destination, edit.destination, edit),
      products = this.products.headOption
        .map(product =>
          ProductWithMatchAndEdit(
            name = product.name,
            manualEdit = edit.product.map(p =>
              ProductEdited(id = p.id, quantities = p.quantities, editedBy = edit.userId, editedAt = edit.timestamp),
            ),
            matcherResult = product.matcherResult,
          ),
        )
        .toList,
    )
  }
}

object DataImportBolEdited {
  def apply(matchesApplied: DataImportBol): DataImportBolEdited =
    matchesApplied
      .into[DataImportBolEdited]
      .transform(
        Field.const(_.verified, false),
        Field.const(_.visible, true),
        Field.computed(_.vessel, x => x.vesselInfo.map(EntityWithMatchAndEdit.apply)),
        Field.computed(_.consignee, x => x.consignee.map(EntityWithMatchAndEdit.apply)),
        Field.computed(_.shipper, x => x.shipper.map(EntityWithMatchAndEdit.apply)),
        Field.computed(_.notifyParty, x => x.notifyParty.map(EntityWithMatchAndEdit.apply)),
        Field.computed(_.origin, x => x.origin.map(EntityWithMatchAndEdit.apply)),
        Field.computed(_.destination, x => x.destination.map(EntityWithMatchAndEdit.apply)),
        Field.computed(_.products, x => x.products.map(ProductWithMatchAndEdit.apply)),
      )
}
