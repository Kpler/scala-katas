val javaVersion = "11"
val appScalaVersion = "2.13.6"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Ywarn-dead-code",
)

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.kpler",
      scalaVersion := appScalaVersion,
    )),
    name := "TicTacToe"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % Test
