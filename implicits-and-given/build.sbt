ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "implicits-and-given",
    libraryDependencies ++= Seq(
      dependencies.catsEffect,
      testDependencies.scalatest,
      testDependencies.catsEffectTestSpecs,
      testDependencies.munitCatsEffect,
    )
  )

lazy val dependencies = new {

  val catsEffect = "org.typelevel" %% "cats-effect" % "3.5.0"

}

lazy val testDependencies = new {

  val scalatest = "org.scalatest" %% "scalatest" % "3.2.15" % Test
  val catsEffectTestSpecs = "org.typelevel" %% "cats-effect-testing-specs2" % "1.4.0" % Test
  val munitCatsEffect = "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test
}
