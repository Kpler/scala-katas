package com.kpler.scala.kata.fs2

import vulcan.Codec
import vulcan.generic.*

case class KplerProduct(id: Int, name: String) derives Codec
