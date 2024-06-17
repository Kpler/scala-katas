package com.kpler.scala.kata.fs2

import cats.effect.{ ExitCode, IO, IOApp, ResourceIO }
import com.kpler.scala.kata.fs2.kafka.KafkaFs2Consumer.{ logIfError, nonFailingConsumer, rightValueOnly }
import com.kpler.scala.kata.fs2.kafka.KafkaFs2Producer.producerResource
import com.kpler.scala.kata.fs2.kafka.Serdes
import fs2.{ Chunk, Stream }
import fs2.kafka.vulcan.{ AvroSettings, SchemaRegistryClientSettings }
import fs2.kafka.{ KafkaConsumer, KafkaProducer, ProducerRecord, ValueDeserializer, ValueSerializer }
import org.typelevel.log4cats.SelfAwareStructuredLogger
import org.typelevel.log4cats.slf4j.Slf4jFactory
import com.kpler.scala.kata.fs2.Basics.productToProductWithAliases
import fs2.kafka.consumer.KafkaConsumeChunk

object KafkaMain extends IOApp {

  private val logger: SelfAwareStructuredLogger[IO] =
    Slf4jFactory.create[IO].getLoggerFromClass(classOf[KafkaMain.type])

  override def run(args: List[String]): IO[ExitCode] =

    val avroSettings: AvroSettings[IO] = AvroSettings(SchemaRegistryClientSettings[IO]("http://localhost:8081"))

    given ResourceIO[ValueDeserializer[IO, KplerProduct]] =
      Serdes.valueDeserializer(avroSettings)

    given ResourceIO[ValueSerializer[IO, KplerProductWithAliases]] =
      Serdes.valueSerializer(avroSettings)

    val consumer: Stream[IO, KafkaConsumer[IO, Int, Either[Throwable, KplerProduct]]] =
      nonFailingConsumer[Int, KplerProduct]

    val producer: ResourceIO[KafkaProducer[IO, Int, KplerProductWithAliases]] =
      producerResource[Int, KplerProductWithAliases]

    val productToAliases: Int => IO[List[String]] =
      productId =>
        IO.pure(
          (for (i <- 0 until 2) yield s"alias $i of product $productId").toList,
        )

    producer.use { producer =>
      consumer.consumeChunk { chunk =>
        Stream
          .foldable(chunk)
          .evalTap(logIfError(logger.error))
          .collect(rightValueOnly)
          .map(_.value)
          .evalMap(productToProductWithAliases(productToAliases))
          .map(withAlias => ProducerRecord("kata-fs2.output", withAlias.id, withAlias))
          .compile
          .to(Chunk)
          .flatTap(producer.produce)
          .as(KafkaConsumeChunk.CommitNow)
      }
    }

}
