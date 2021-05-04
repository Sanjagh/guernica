import Dependencies.{scalaTest, _}

ThisBuild / scalaVersion := "2.13.5"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "pro.sanjagh"
ThisBuild / organizationName := "sanjagh"

lazy val root = (project in file("."))
  .settings(name := "guernica")

lazy val engine = (project in file("engine"))
  .settings(
    name := "engine",
    libraryDependencies ++= Akka.all,
    libraryDependencies ++= Akka.test,
    libraryDependencies += scalaTest,
    libraryDependencies += slf4j,
    libraryDependencies += figlet
  )
