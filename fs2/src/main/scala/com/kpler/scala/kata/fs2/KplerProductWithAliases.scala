package com.kpler.scala.kata.fs2

import vulcan.Codec
import vulcan.generic.*

case class KplerProductWithAliases(id: Int, name: String, aliases: List[String]) derives Codec
