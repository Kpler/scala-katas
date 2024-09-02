package com.kpler.training.scala.kata.variance

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Serde

import scala.jdk.CollectionConverters.*

class AvroSchemaTest {
  val registryProps = Map(
    AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG -> "mock://testurl",
  )

  val valueSerde: Serde[GenericRecord] = {
    val genericAvroSerde = new GenericAvroSerde
    genericAvroSerde.configure(
      registryProps.asJava,
      false,
    )
    genericAvroSerde
  }

  val keySerde: Serde[GenericRecord] = {
    val genericAvroSerde = new GenericAvroSerde
    genericAvroSerde.configure(
      registryProps.asJava,
      true,
    )
    genericAvroSerde
  }
}
