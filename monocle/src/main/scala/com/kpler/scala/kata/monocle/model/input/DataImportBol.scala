package com.kpler.scala.kata.monocle.model.input

final case class DataImportBol(
  masterBOLNumber: String,
  importDate: String,
  actualArrivalDate: String,
  estimatedArrivalDate: String,
  vesselInfo: Option[VesselInfoImportBol],
  consignee: Option[Player],
  shipper: Option[Player],
  notifyParty: Option[Player],
  origin: Option[Zone],
  destination: Option[Zone],
  products: List[Product],
  raw: String,
)
