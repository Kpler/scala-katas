ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.2"

assembly / assemblyOutputPath := file("target/fs2-kata.jar")

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}

scalacOptions ++= Seq(
  "-encoding",
  "utf8",
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Ykind-projector",
  "-Wunused:imports,privates,params,locals,implicits,explicits",
  "-Wvalue-discard",
  "-Wnonunit-statement",
)

Test / scalacOptions --= Seq(
  "-Wnonunit-statement", // does not work with assertions in scalatest, which always return an Assertion
)

fork in Global := true

val versions = new {
  val scalatestVersion = "3.2.18"
  val fs2 = "3.10.2"
  val fs2Kafka = "3.5.1"
}

lazy val root = (project in file("."))
  .settings(
    resolvers += "confluent" at "https://packages.confluent.io/maven/",
    name := "fs2-kata",
    libraryDependencies ++= Seq(

      // logging
      "ch.qos.logback" % "logback-classic" % "1.5.6",
      "org.typelevel" %% "log4cats-slf4j" % "2.6.0",

      // fs2
      "co.fs2" %% "fs2-core" % versions.fs2,
      "co.fs2" %% "fs2-io" % versions.fs2,
      "org.typelevel" %% "cats-effect" % "3.5.2",


      // kafka
      "com.github.fd4s" %% "fs2-kafka" % versions.fs2Kafka,

      // avro
      "com.github.fd4s" %% "fs2-kafka-vulcan" % versions.fs2Kafka,
      "com.github.fd4s" %% "vulcan-generic" % "1.10.1",

      // Test
      "org.scalactic" %% "scalactic" % versions.scalatestVersion % Test,
      "org.scalatest" %% "scalatest" % versions.scalatestVersion % Test,
    ),
  )
