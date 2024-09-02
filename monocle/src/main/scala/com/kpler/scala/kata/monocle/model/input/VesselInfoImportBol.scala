package com.kpler.scala.kata.monocle.model.input

import com.kpler.scala.kata.monocle.model.shared.Entity

final case class VesselInfoImportBol(conveyanceID: String, kplerVessel: Option[Entity])
