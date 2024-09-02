package com.kpler.scala.kata.monocle.model

import com.kpler.scala.kata.monocle.model.edit.Edit
import com.kpler.scala.kata.monocle.model.input.ImportBolMatchesApplied
import com.kpler.scala.kata.monocle.model.output.{ DataImportBolEdited, ImportBolEditsApplied }

object Joiner {

  def join(input: ImportBolMatchesApplied, patch: Edit): ImportBolEditsApplied = ImportBolEditsApplied(
    input.header,
    DataImportBolEdited(input.data).applyEdit(patch),
  )
}
