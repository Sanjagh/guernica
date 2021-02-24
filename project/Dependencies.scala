import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.2" % Test
  lazy val figlet = "com.colofabrix.scala" %% "figlet4s-core" % "0.2.0"


  object Akka {
    private val AkkaVersion = "2.6.8"
    private val AkkaHttpVersion = "10.2.2"

    lazy val actor = "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
    lazy val stream = "com.typesafe.akka" %% "akka-stream" % AkkaVersion
    lazy val http = "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
    lazy val json = "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion

    lazy val test = Seq("com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % Test)
  }

}
