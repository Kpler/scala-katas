package com.kpler.scala.kata.monocle

import com.kpler.scala.kata.monocle.model.{ Joiner, MonocleJoiner }
import com.kpler.scala.kata.monocle.model.edit.{ Edit, EditedEntity }
import com.kpler.scala.kata.monocle.model.input.{ ImportBolMatchesApplied, Player, Product, VesselInfoImportBol }
import com.kpler.scala.kata.monocle.model.output.{
  DataImportBolEdited,
  EntityEdit,
  EntityWithMatchAndEdit,
  ImportBolEditsApplied,
  ProductWithMatchAndEdit,
}
import com.kpler.scala.kata.monocle.model.shared.{ Entity, EntityWithQuantity, Quantity, QuantityUnit }
import com.kpler.scala.kata.monocle.serializer.JsonRW.given
import com.kpler.scala.kata.monocle.serializer.JsonReaders.given
import monocle.internal.IsoFields
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import upickle.default.read
import monocle.syntax.all.focus
import com.kpler.scala.kata.monocle.model.MonocleJoiner.{
  applyEditToMergedEntity,
  inputDataFieldToMergedEntity,
  inputDataToOutputData,
  productToMergedProduct,
  given,
}

import java.io.File
import java.util.UUID

class MonocleJoinerSpec extends AnyFunSuite with should.Matchers {

  given editorId: UUID = UUID.randomUUID()

  given timestamp: String = "2023-11-10T15:48:27.414951358Z"

  test("Warming up 1: the code to refactor merges an input and an edit to yield content of output.json") {

    Joiner.join(
      read[ImportBolMatchesApplied](new File("src/test/resources/input.json")),
      read[Edit](new File("src/test/resources/edit.json")),
    ) should be(
      read[ImportBolEditsApplied](new File("src/test/resources/output.json")),
    )
  }

  test("Warming up 2: A Monocle Iso should extract a tuple from a case class instance") {

    IsoFields[Player].get(
      Player("matched value", Some(Entity(42, "matched entity name", 80.0))),
    ) should be(("matched value", Some(Entity(42, "matched entity name", 80.0))))
  }

  import monocle.syntax.all.focus

  test("Warming up 3: Monocle focus should replace a nested field in a case class instance") {

    Entity(42, "matched entity name", 80.0)
      .focus(_.confidence)
      .replace(100.0) should be(Entity(42, "matched entity name", 100.0))
  }

  test(
    "Exercise 1: inputDataFieldToMergedEntity should transform a unmatched Player (input) into a EntityWithMatchAndEdit (output with no edit)",
  ) {

    inputDataFieldToMergedEntity(Player("matched value", None)) should be(
      EntityWithMatchAndEdit("matched value", None, None),
    )
  }

  test(
    "Exercise 1: inputDataFieldToMergedEntity should transform a matched Player (input) into a EntityWithMatchAndEdit (output with no edit)",
  ) {

    inputDataFieldToMergedEntity(
      Player(
        "matched value",
        Some(Entity(42, "matched entity name", 80.0)),
      ),
    ) should be(
      EntityWithMatchAndEdit(
        "matched value",
        None,
        Some(Entity(42, "matched entity name", 80.0)),
      ),
    )
  }

  test(
    "Exercise 1: inputDataFieldToMergedEntity should transform a unmatched Vessel (input) into a EntityWithMatchAndEdit (output with no edit)",
  ) {

    inputDataFieldToMergedEntity(VesselInfoImportBol("matched vessel", None)) should be(
      EntityWithMatchAndEdit("matched vessel", None, None),
    )
  }

  test(
    "Exercise 2: productToMergedProduct should transform a unmatched Product (input) into a ProductWithMatchAndEdit (output with no edit)",
  ) {

    productToMergedProduct(
      Product("unmatched product", None),
    ) should be(ProductWithMatchAndEdit("unmatched product", None, None))
  }

  test(
    "Exercise 2: productToMergedProduct should transform a matched Product (input) into a ProductWithMatchAndEdit (output with no edit)",
  ) {

    productToMergedProduct(
      Product(
        "matched product",
        Some(
          EntityWithQuantity(
            12,
            "quantity name",
            80.0,
            List(Quantity("2M", QuantityUnit.BBL)),
          ),
        ),
      ),
    ) should be(
      ProductWithMatchAndEdit(
        "matched product",
        None,
        Some(
          EntityWithQuantity(
            12,
            "quantity name",
            80.0,
            List(Quantity("2M", QuantityUnit.BBL)),
          ),
        ),
      ),
    )
  }

  test(
    "Exercise 3: inputDataToOutputData should transform a DataImportBol (input) into a DataImportBolEdited (output with no edit)",
  ) {

    val input = read[ImportBolMatchesApplied](new File("src/test/resources/input.json"))
    val output = inputDataToOutputData(input.data)
    output should be(DataImportBolEdited(input.data)) // DataImportBolEdited.apply is the code to refactor
  }

  test("Exercise 4: applyEditToMergedEntity should left an entity untouched if no id is supplied in the edition") {

    val fromInput = EntityWithMatchAndEdit("matched vessel", None, Some(Entity(42, "matched entity name", 80.0)))
    applyEditToMergedEntity(fromInput, EditedEntity(None)) should be(fromInput)
  }

  test(
    "Exercise 4: applyEditToMergedEntity should update the 'manualEdit' field of an entity if an id is supplied in the edition",
  ) {

    val fromInput = EntityWithMatchAndEdit("matched vessel", None, Some(Entity(42, "matched entity name", 80.0)))
    applyEditToMergedEntity(fromInput, EditedEntity(Some(56))) should be(
      EntityWithMatchAndEdit(
        "matched vessel",
        Some(EntityEdit(Some(56), editorId, timestamp)),
        Some(Entity(42, "matched entity name", 80.0)),
      ),
    )
  }

  test(
    "Exercise 5: MonocleJoiner.join should yield the same output as Joiner.join",
  ) {

    val input = read[ImportBolMatchesApplied](new File("src/test/resources/input.json"))
    val edit = read[Edit](new File("src/test/resources/edit.json"))
    val withMonocle = MonocleJoiner.join(input, edit)
    val noMonocle = Joiner.join(input, edit)
    withMonocle should be(noMonocle)
  }
}
