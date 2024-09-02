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
    name := "typeclass-pattern",
    libraryDependencies ++= Seq(
      // cats effect
      "org.typelevel" %% "cats-core" % "2.10.0",


      // Test
      "org.scalactic" %% "scalactic" % "3.2.17" % Test,
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,

    ),
  )
