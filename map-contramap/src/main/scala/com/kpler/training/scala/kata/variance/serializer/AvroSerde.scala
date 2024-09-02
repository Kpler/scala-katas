package com.kpler.training.scala.kata.variance.serializer

import com.sksamuel.avro4s
import com.sksamuel.avro4s.*
import org.apache.avro.Schema
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.{Deserializer, Serializer}



/** Integrate avro4s and kafka
  */
object AvroSerde {
  def serializer[T: Encoder](using
    schema: Schema,
    genericRecord: Serializer[GenericRecord],
  ): Serializer[T] = (topic: String, data: T) => genericRecord.serialize(topic, ToRecord.apply(schema).to(data))

  def deserializer[T: Decoder](using
    schema: Schema,
    genericRecordDeserializer: Deserializer[GenericRecord],
  ): Deserializer[T] = (topic: String, data: Array[Byte]) =>
    FromRecord.apply[T](schema).from(genericRecordDeserializer.deserialize(topic, data))
}
