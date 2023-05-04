import Dependencies._

ThisBuild / version := "0.0.1"
ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "ecst-sse-pipeline-demo",
    idePackagePrefix := Some("dm.drsdgdbye"),
    Compile / mainClass := Some("dm.drsdgdbye.Main"),
    libraryDependencies ++= implDependencies
  )
