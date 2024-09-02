package com.kpler.scala.kata.monocle.model.output

import com.kpler.scala.kata.monocle.model.edit.EditedEntity
import com.kpler.scala.kata.monocle.model.input.{ Player, VesselInfoImportBol, Zone }
import com.kpler.scala.kata.monocle.model.shared.Entity

import java.util.UUID

final case class EntityWithMatchAndEdit(
  name: String,
  manualEdit: Option[EntityEdit],
  matcherResult: Option[Entity],
) {
  def applyEdit(editedEntity: EditedEntity, editedBy: UUID, editedAt: String): EntityWithMatchAndEdit = {
    this.copy(manualEdit = Some(EntityEdit(id = editedEntity.id, editedBy = editedBy, editedAt = editedAt)))
  }
}

object EntityWithMatchAndEdit {
  def apply(vessel: VesselInfoImportBol): EntityWithMatchAndEdit =
    EntityWithMatchAndEdit(
      name = vessel.conveyanceID,
      manualEdit = None,
      matcherResult = vessel.kplerVessel.map(x => Entity(id = x.id, name = x.name, confidence = x.confidence)),
    )

  def apply(player: Player): EntityWithMatchAndEdit =
    EntityWithMatchAndEdit(
      name = player.name,
      manualEdit = None,
      matcherResult = player.kplerPlayer.map(x => Entity(id = x.id, name = x.name, confidence = x.confidence)),
    )

  def apply(zone: Zone): EntityWithMatchAndEdit =
    EntityWithMatchAndEdit(
      name = zone.name,
      manualEdit = None,
      matcherResult = zone.kplerZone.map(x => Entity(id = x.id, name = x.name, confidence = x.confidence)),
    )

  /** Construct a EntityWithMatchAndEdit with only the value used to find a match
    *
    * No match was found and there is no edit applied
    * @param usedForMatching - the value to find a match for this entity
    * @return a EntityWithMatchAndEdit with only the value that was used for the matching
    */
  def withMatchedValueButNoMatch(usedForMatching: String): EntityWithMatchAndEdit =
    EntityWithMatchAndEdit(usedForMatching, None, None)

  /** Construct a EntityWithMatchAndEdit with the value used to find a match and the matched entity found
    *
    * No edit is applied
    *
    * @param usedForMatching - the value to find a match for this entity
    * @param matched - the matched entity
    * @return a EntityWithMatchAndEdit with the value that was used for the matching and the matched entity found
    */
  def withMatchedValueAndMatch(usedForMatching: String, matched: Entity): EntityWithMatchAndEdit =
    EntityWithMatchAndEdit(usedForMatching, None, Some(matched))
}
