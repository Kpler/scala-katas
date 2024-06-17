package com.kpler.scala.kata.fs2.kafka

import cats.effect.{ IO, ResourceIO }
import fs2.kafka.producer.MkProducer
import fs2.kafka.{ KafkaProducer, KeySerializer, ProducerSettings, ValueSerializer }

object KafkaFs2Producer {

  def producerResource[K, V](using
    keySerializer: ResourceIO[KeySerializer[IO, K]],
    valueSerializer: ResourceIO[ValueSerializer[IO, V]],
    mkProducer: MkProducer[IO],
  ): ResourceIO[KafkaProducer[IO, K, V]] = KafkaProducer
    .resource(
      ProducerSettings[IO, K, V]
        .withBootstrapServers("localhost:9092")
        .withClientId("KplerProduct to KplerProductWithAliases"),
    )

}
