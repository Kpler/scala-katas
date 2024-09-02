package com.kpler.scala.kata.monocle.serializer

import com.kpler.scala.kata.monocle.model.output.*
import com.kpler.scala.kata.monocle.model.shared.*
import upickle.default.{ macroRW, reader, writer, ReadWriter as RW, Reader as R, Writer as W }

object JsonRW {

  given optionReader[T: R]: R[Option[T]] = {
    new R.Delegate[Any, Option[T]](reader[T].map(Option.apply)) {
      override def visitNull(index: Int) = None
    }
  }

  given optionWriter[T: W]: W[Option[T]] = writer[T].comap(_.orNull.asInstanceOf[T])

  // shared
  given RW[Entity] = macroRW[Entity]

  given RW[EntityWithQuantity] = macroRW[EntityWithQuantity]

  given RW[Header] = macroRW[Header]

  given R[QuantityUnit] = reader[String].map(QuantityUnit.valueOf)
  given W[QuantityUnit] = writer[String].comap(_.toString)

  given RW[Quantity] = macroRW[Quantity]

  // output
  given RW[EntityEdit] = macroRW[EntityEdit]

  given RW[EntityWithMatchAndEdit] = macroRW[EntityWithMatchAndEdit]

  given RW[ProductEdited] = macroRW[ProductEdited]

  given RW[ProductWithMatchAndEdit] = macroRW[ProductWithMatchAndEdit]

  given RW[DataImportBolEdited] = macroRW[DataImportBolEdited]

  given RW[ImportBolEditsApplied] = macroRW[ImportBolEditsApplied]
}
