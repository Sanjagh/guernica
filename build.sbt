import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "pro.sanjagh"
ThisBuild / organizationName := "sanjagh"

lazy val root = (project in file("."))
  .settings(
    name := "guernica",
    libraryDependencies += scalaTest % Test
  )
