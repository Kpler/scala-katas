ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.4"

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
      "org.typelevel" %% "cats-core" % "2.13.0",


      // Test
      "org.scalactic" %% "scalactic" % "3.2.20" % Test,
      "org.scalatest" %% "scalatest" % "3.2.20" % Test,

    ),
  )
