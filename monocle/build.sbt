ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

ThisBuild / scalafmtOnCompile := true

fork in Global := true

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Wunused:imports,privates",
)

lazy val root = (project in file("."))
  .settings(
    name := "monocle-kata",
    libraryDependencies ++= Seq(
      // cats effect
      "org.typelevel" %% "cats-core" % "2.10.0",

      "io.github.arainko" %% "ducktape" % "0.1.11",

      "dev.optics" %% "monocle-core" % "3.2.0",
      "dev.optics" %% "monocle-macro" % "3.2.0",

      // Test
      // json
      "com.lihaoyi" %% "upickle" % "3.1.3" % Test,
      "org.scalactic" %% "scalactic" % "3.2.17" % Test,
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,

    ),
  )
