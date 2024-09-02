package com.kpler.scala.kata.monocle.serializer

import com.kpler.scala.kata.monocle.model.edit.{ Edit, EditedEntity, ProductEdit }
import com.kpler.scala.kata.monocle.model.input.*
import com.kpler.scala.kata.monocle.model.shared.*
import com.kpler.scala.kata.monocle.serializer.JsonRW.given
import upickle.default.{ macroR, Reader as R }

object JsonReaders {

  // input
  given R[Player] = macroR[Player]

  given R[Product] = macroR[Product]

  given R[Zone] = macroR[Zone]

  given R[VesselInfoImportBol] = macroR[VesselInfoImportBol]

  given R[DataImportBol] = macroR[DataImportBol]

  given R[ImportBolMatchesApplied] = macroR[ImportBolMatchesApplied]

  // edit
  given R[EditedEntity] = macroR[EditedEntity]

  given R[ProductEdit] = macroR[ProductEdit]

  given R[Edit] = macroR[Edit]

}
