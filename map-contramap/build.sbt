ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    resolvers += "confluent" at "https://packages.confluent.io/maven/",
    name := "map-contramap",
    libraryDependencies ++= Seq(
      dependencies.catsEffect,
      //dependencies.avroSerializer,
      dependencies.kafkaStreamAvroSerde,
      dependencies.avro4s,
      dependencies.kafkaClients,
      dependencies.upickle,
      testDependencies.scalatest,
      testDependencies.catsEffectTestSpecs,
      testDependencies.munitCatsEffect,
    )
  )

lazy val dependencies = new {

  val catsEffect = "org.typelevel" %% "cats-effect" % "3.5.0"
  val avroSerializer = "io.confluent" % "kafka-avro-serializer" % "7.5.0"
  val kafkaStreamAvroSerde = "io.confluent" % "kafka-streams-avro-serde" % "7.5.0"
  val avro4s = "com.sksamuel.avro4s" %% "avro4s-core" % "5.0.5"
  val kafkaClients = "org.apache.kafka" % "kafka-clients" % "3.5.1"
  val upickle = "com.lihaoyi" %% "upickle" % "3.1.3"

}

lazy val testDependencies = new {

  val scalatest = "org.scalatest" %% "scalatest" % "3.2.15" % Test
  val catsEffectTestSpecs = "org.typelevel" %% "cats-effect-testing-specs2" % "1.4.0" % Test
  val munitCatsEffect = "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test
}
