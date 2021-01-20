import Dependencies.{scalaTest, _}

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "pro.sanjagh"
ThisBuild / organizationName := "sanjagh"

lazy val root = (project in file("."))
  .settings(name := "guernica")

lazy val engine = (project in file("engine"))
  .settings(
    name := "engine",
    libraryDependencies ++= Seq(Akka.actor, Akka.stream, Akka.http),
    libraryDependencies += scalaTest % Test,
  )