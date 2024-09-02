package com.kpler.scala.kata.monocle.model

import com.kpler.scala.kata.monocle.model.edit.{ Edit, EditedEntity, ProductEdit }
import com.kpler.scala.kata.monocle.model.input.*
import com.kpler.scala.kata.monocle.model.output.{
  DataImportBolEdited,
  EntityEdit,
  EntityWithMatchAndEdit as MergedEntity,
  ImportBolEditsApplied,
  ProductEdited,
  ProductWithMatchAndEdit,
}
import com.kpler.scala.kata.monocle.model.shared.Entity
import io.github.arainko.ducktape.{ into, Field }
import monocle.Iso
import monocle.internal.IsoFields

import java.util.UUID

/** The purpose of the 'MonocleJoiner' object is to serve the same purpose as 'Joiner' but with less boiler plate code.
  *
  * What 'Joiner' does is actually quite simple: It merges one message coming the apply-matches topic with one message coming
  * from the edits topic.
  * The join is made on the key of the topics (ie the master BOL number for import)
  * Example:
  * apply-matches:
  *
  * {
  *   "header": {
  *   ...
  *   },
  *   "data": {
  *     "masterBOLNumber": "CRNV33M120300018",
  *     ...
  *     "shipper": {
  *      "name": "CARNIVAL CRUISE LINES",
  *       "kplerPlayer": {
  *         "id": 16678,
  *         "name": "Carnival Corporation",
  *         "confidence": 42.593406677246094
  *       }
  *     }
  *   }
  * }
  *
  * edits:
  *  {
  *    "userId": "68007b16-82d9-4b0d-8707-be48505b84aa",
  *    "timestamp": "2023-11-10T15:48:27.414951358Z",
  *    "verified": true,
  *    "visible": true,
  *    "shipper": {
  *      "id": 86
  *    }
  *  }
  * merged (apply-edits):
  * {
  *  "header": {
  *  },
  *  "data": {
  *    "masterBOLNumber": "CRNV33M120300018",
  *    "verified": true,
  *    "visible": true,
  *    "shipper": {
  *      "name": "CARNIVAL CRUISE LINES",
  *      "manualEdit": {
  *        "id": 86,
  *        "editedBy": "68007b16-82d9-4b0d-8707-be48505b84aa",
  *        "editedAt": "2023-11-10T15:48:27.414951358Z"
  *      },
  *      "matcherResult": {
  *        "id": 16678,
  *        "name": "Carnival Corporation",
  *        "confidence": 42.593407
  *      }
  *    }
  *  }
  * }
  * In the 'shipper' output field:
  *  - 'name' and 'matcherResult' come from 'apply-matches'
  *  - 'manualEdit' come from 'edits'
  *
  * The root model case class for 'apply-matches' is ImportBolMatchesApplied. It's referenced as 'input' in the function names
  * The model case class for 'edit' is Edit. It's referenced as 'edit' in the function names
  * The root model case class for 'apply-edits' is ImportBolEditsApplied. It's referenced as 'output' or 'merged' in the function names
  * To refactor the Joiner code, 'MonocleJoiner' relies on the Monocle library: https://github.com/optics-dev/Monocle.
  */
object MonocleJoiner {

  private type IsoTupleStrOptEntity[T] = Iso[T, (String, Option[Entity])]

  given IsoTupleStrOptEntity[Player] = IsoFields[Player]

  given IsoTupleStrOptEntity[VesselInfoImportBol] = IsoFields[VesselInfoImportBol]

  given IsoTupleStrOptEntity[Zone] = IsoFields[Zone]

  /** Transform t into a MergedEntity.
    *
    * The function should use the 'toTuple' Monocle Iso to extract a tuple of (String, Option[Entity]) out of t.
    * If the Entity is set, The function could call the Helper function MergedEntity.withMatchedValueAndMatch
    * Otherwise it could call MergedEntity.withMatchedValueButNoMatch
    * @param t - the instance to transform into a MergedEntity
    * @param toTuple - The Monocle Iso to extract a tuple of (String, Option[Entity]) out of t
    * @tparam T - The type of t
    * @return - The transformation of t as a MergedEntity
    */
  def inputDataFieldToMergedEntity[T](t: T)(using toTuple: IsoTupleStrOptEntity[T]): MergedEntity = ???

  /** Same as 'inputDataFieldToMergedEntity' but specific to product
    *
    * The output model class is 'ProductWithMatchAndEdit' and not MergedEntity
    * THe helper function one may use are:
    *  - ProductWithMatchAndEdit.withMatchedValueButNoMatch
    *  - ProductWithMatchAndEdit.withMatchedValueAndMatch
    * @param product - The instance to transform into a ProductWithMatchAndEdit
    * @return - The transformation of product as a ProductWithMatchAndEdit
    */
  def productToMergedProduct(product: Product): ProductWithMatchAndEdit = ???

  /** Transform a DataImportBol into a DataImportBolEdited.
    *
    * inputDataFieldToMergedEntity should be called to map the fields:
    * - vessel
    * - consignee
    * - shipper
    * - notifyParty
    * - origin
    * - destination
    *
    * productToMergedProduct should be called to map products
    *
    * One might consider using the ducktape library to initiate the transformation from
    * 'DataImportBol' into 'DataImportBolEdited': https://index.scala-lang.org/arainko/ducktape
    * Ducktape can copy the fields with the same names automatically (masterBOLNumber, importDate, raw ...)
    * 'verified' and 'visible' must be set to 'false' and 'true' respectively
    * @param input - The DataImportBol to transform into a DataImportBolEdited
    */
  def inputDataToOutputData(input: DataImportBol): DataImportBolEdited =  ???

  private type IsoTupleEdition[T] = Iso[T, Tuple1[Option[Int]]]

  import monocle.syntax.all.focus

  /** Apply the edition t to the entity 'fromInput'
    *
    * The function should use the 'toTuple' Monocle Iso to extract a tuple of (Option[Int]) out of t.
    * If the edited id is defined, the function should set the 'manualEdit' field of 'fromInput'
    * One solution to achieve this is to use the 'focus' syntax of Monocle (see Test 'Warming up 3')
    * Like 'inputDataFieldToMergedEntity' this function is called many times, thus userId and timestamp are supplied
    * with 'using' to avoid to pass their value every time
    * @param fromInput - The entity to apply the edition on
    * @param t - The edition to apply on 'fromInput'
    * @param toTuple - The Monocle Iso to extract the edited id out of t
    * @param userId - The id of user who made the edition
    * @param timestamp - The time at which the user made the edition
    */
  def applyEditToMergedEntity[T](fromInput: MergedEntity, t: T)(using
    toTuple: IsoTupleEdition[T],
    userId: UUID,
    timestamp: String,
  ): MergedEntity = ???

  /** Same as applyEditToMergedEntity but for Products
    * @param fromInput - The product to apply the edition on
    * @param edited - The edition to apply on 'fromInput'
    * @param userId - The id of user who made the edition
    * @param timestamp - The time at which the user made the edition
    */
  def applyEditToMergedProduct(fromInput: ProductWithMatchAndEdit, edited: ProductEdit)(using
    userId: UUID,
    timestamp: String,
  ): ProductWithMatchAndEdit = ???

  import cats.syntax.all.catsSyntaxTuple2Semigroupal

  given IsoTupleEdition[EditedEntity] = IsoFields[EditedEntity]

  /** Join 'input' and 'patch' to yield a ImportBolEditsApplied
    *
    * The function should call all the functions written above.
    * It should first call 'inputDataToOutputData' to initiate the 'DataImportBolEdited' output
    * and then focus on every editable field (vessel, shipper ...) to apply the edition with applyEditToMergedEntity
    * and applyEditToMergedProduct
    * Again the 'focus' syntax of Monocle (see Test 'Warming up 3') might be used here combined with 'modify' and not 'replace'
    * About the products, the edition should be applied on the first product of the input and the resulting list should contain at most one entry
    * for the 'visible' and 'verified' fields, the output should take the value from the edition if set, the default one otherwise;
    * To extract a tuple of values from 2 Option with no pattern matching, you might consider using mapN:
    * Example:
    *  def sum(a: Int, b: Int) = a + b
    *  (Option(4), Option(6)).mapN(sum) => Some(10)
    * This can be useful to call applyEditToMergedEntity
    * @param input - the BOL to apply the edition on
    * @param patch - the edit to apply on the BOL
    * @return - the edited BOL
    */
  def join(input: ImportBolMatchesApplied, patch: Edit): ImportBolEditsApplied = ???

}
