ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.2"

fork in Global := true

val versions = new {
  val scalatestVersion = "3.2.18"
}

lazy val root = (project in file("."))
  .settings(
    name := "intro-fp",
    libraryDependencies ++= Seq(
      
      
      // Test
      "org.scalactic" %% "scalactic" % versions.scalatestVersion % Test,
      "org.scalatest" %% "scalatest" % versions.scalatestVersion % Test,
    ),
  )
