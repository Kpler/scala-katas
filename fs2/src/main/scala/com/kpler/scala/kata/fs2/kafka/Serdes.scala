package com.kpler.scala.kata.fs2.kafka

import cats.effect.{ IO, ResourceIO }
import fs2.kafka.vulcan.{ avroDeserializer, avroSerializer, AvroSettings }
import fs2.kafka.{ ValueDeserializer, ValueSerializer }
import vulcan.Codec

object Serdes {

  def valueDeserializer[A: Codec](avroSettings: AvroSettings[IO]): ResourceIO[ValueDeserializer[IO, A]] =
    avroDeserializer.forValue(avroSettings)

  def valueSerializer[A: Codec](avroSettings: AvroSettings[IO]): ResourceIO[ValueSerializer[IO, A]] =
    avroSerializer.forValue(avroSettings)

}
