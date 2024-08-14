
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "sprint-name-generator"
  ).settings(libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0")

unmanagedResourceDirectories in Compile += baseDirectory.value / "src" / "main" / "resources"

