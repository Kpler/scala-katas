package com.kpler.scala.kata.fs2.kafka

import cats.Show
import cats.effect.{ IO, ResourceIO }
import cats.syntax.all.{ toContravariantOps, toShow }
import fs2.kafka.*
import fs2.kafka.consumer.MkConsumer

object KafkaFs2Consumer {

  private type ValueDeserializeError = Throwable

  given consumerRecordShow[K, V]: Show[ConsumerRecord[K, V]] = Show[String].contramap { record =>
    Seq(
      Some("topic" -> record.topic),
      Some("partition" -> record.partition.toString),
      Some("offset" -> record.offset.toString),
      Option.when(record.timestamp.nonEmpty)("timestamp" -> record.timestamp.toString),
    ).flatten.toMap.show
  }

  def nonFailingConsumer[K, V](using
    keyDeserializer: ResourceIO[KeyDeserializer[IO, K]],
    valueDeserializer: ResourceIO[ValueDeserializer[IO, V]],
    mkConsumer: MkConsumer[IO],
  ): fs2.Stream[IO, KafkaConsumer[IO, K, Either[ValueDeserializeError, V]]] =
    KafkaConsumer
      .stream(
        ConsumerSettings[IO, K, Either[ValueDeserializeError, V]](keyDeserializer, valueDeserializer.map(_.attempt))
          .withBootstrapServers("localhost:9092")
          .withAutoOffsetReset(AutoOffsetReset.Earliest)
          .withGroupId("KplerProduct to KplerProductWithAliases"),
      )
      .subscribeTo("kata-fs2.input")

  def nonFailingConsumedStream[K, V](using
    keyDeserializer: ResourceIO[KeyDeserializer[IO, K]],
    valueDeserializer: ResourceIO[ValueDeserializer[IO, V]],
    mkConsumer: MkConsumer[IO],
  ): fs2.Stream[IO, ConsumerRecord[K, Either[ValueDeserializeError, V]]] =
    nonFailingConsumer.records.map(_.record)

  def logIfError[K, V](
    logFunction: ValueDeserializeError => (=> String) => IO[Unit],
  )(record: ConsumerRecord[K, Either[ValueDeserializeError, V]]): IO[Unit] =
    record.value match {

      case Left(error) => logFunction(error)(s"Some error occurred while reading the record at: ${record.show}")
      case Right(_) => IO.unit
    }

  def rightValueOnly[K, V]: PartialFunction[
    ConsumerRecord[K, Either[ValueDeserializeError, V]],
    ConsumerRecord[K, V],
  ] = { record =>
    record.value match {
      case Right(value) =>
        ConsumerRecord(
          record.topic,
          record.partition,
          record.offset,
          record.key,
          value,
        )
    }
  }

}
