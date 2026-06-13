ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.2"

scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Ykind-projector",
  "-Wunused:imports,privates,params,locals,implicits,explicits",
  "-Wvalue-discard",
  "-Wnonunit-statement"
)

Test / scalacOptions --= Seq(
  "-Wnonunit-statement" // does not work with assertions in scalatest, which always return an Assertion
)

fork in Global := true

val versions = new {
  val scalatestVersion = "3.2.18"
}

lazy val zioVersion = "2.0.0"

lazy val root = (project in file("."))
  .settings(
    name := "zio-kata",
    libraryDependencies ++= Seq(
      // Logging
      "ch.qos.logback" % "logback-classic" % "1.5.6",
      "org.typelevel" %% "log4cats-slf4j" % "2.6.0",

      // ZIO
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-test" % zioVersion,
      "dev.zio" %% "zio-test-sbt" % zioVersion,
      "dev.zio" %% "zio-streams" % zioVersion,
      "dev.zio" %% "zio-test-junit" % zioVersion,
      "dev.zio" %% "zio-http" % "3.0.0-RC6",

      // Test
      "org.scalactic" %% "scalactic" % versions.scalatestVersion % Test,
      "org.scalatest" %% "scalatest" % versions.scalatestVersion % Test
    )
  )