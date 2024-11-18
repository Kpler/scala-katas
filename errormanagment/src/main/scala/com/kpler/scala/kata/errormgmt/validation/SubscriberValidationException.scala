package com.kpler.scala.kata.errormgmt.validation

import cats.syntax.all.toShow

case class SubscriberValidationException(error: SubscriberValidationError) extends RuntimeException(error.show)
