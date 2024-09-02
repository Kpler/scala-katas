package com.kpler.scala.kata.monocle.model.shared

final case class Quantity(value: String, unit: QuantityUnit)

enum QuantityUnit:
  case BBL, MT, KG, LB, MMBTU, TON, LITER, GAL, M3, MBBLS, LMT
