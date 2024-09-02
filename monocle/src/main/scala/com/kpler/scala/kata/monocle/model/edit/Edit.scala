package com.kpler.scala.kata.monocle.model.edit

import java.util.UUID

case class Edit(
  userId: UUID,
  timestamp: String,
  verified: Option[Boolean],
  visible: Option[Boolean],
  origin: Option[EditedEntity],
  destination: Option[EditedEntity],
  shipper: Option[EditedEntity],
  consignee: Option[EditedEntity],
  notificationParty: Option[EditedEntity],
  vessel: Option[EditedEntity],
  product: Option[ProductEdit],
)
