package com.kpler.scala.kata.monocle.model.output

import com.kpler.scala.kata.monocle.model.input.ImportBolMatchesApplied
import com.kpler.scala.kata.monocle.model.shared.Header

final case class ImportBolEditsApplied(header: Header, data: DataImportBolEdited)

object ImportBolEditsApplied {

  def apply(matchesApplied: ImportBolMatchesApplied): ImportBolEditsApplied =
    ImportBolEditsApplied(header = matchesApplied.header, data = DataImportBolEdited(matchesApplied.data))

}
