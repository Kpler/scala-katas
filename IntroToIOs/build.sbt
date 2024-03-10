ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

ThisBuild / scalafmtOnCompile := true

assembly / assemblyOutputPath := file("target/io-kata.jar")

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}

scalacOptions ++= Seq("-Xmax-inlines", "200")

fork in Global := true

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
)

lazy val root = (project in file("."))
  .settings(
    name := "introduction-to-io-kata",
    libraryDependencies ++= Seq(


      // cats effect
      "org.typelevel" %% "cats-effect" % "3.5.0",
      "org.typelevel" %% "log4cats-slf4j" % "2.5.0",


      // Test
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,
      "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
    ),
  )
