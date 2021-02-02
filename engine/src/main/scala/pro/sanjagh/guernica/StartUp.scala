package pro.sanjagh.guernica

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import com.colofabrix.scala.figlet4s.unsafe.{FIGureOps, Figlet4s, OptionsBuilderOps}
import com.typesafe.config.Config
import pro.sanjagh.guernica.application.rest.{HttpServer, Routes}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import scala.util.{Failure, Success}

object StartUp extends App {
  private implicit val system: ActorSystem[Nothing] = ActorSystem[Nothing](Behaviors.empty, "guernica")
  private implicit val executionContext: ExecutionContextExecutor = system.executionContext
  private implicit val config: Config = system.settings.config

  private val httpServer = for {
    routes <- Routes(config.getString("guernica.api.basePath"))
    server <- HttpServer(routes)
  } yield server

  printBanner()
  startServer()

  private def printBanner(): Unit = Figlet4s
    .builder()
    .text("guernica")
    .render()
    .print()

  private def startServer(): Unit = {
    val bindingFuture = httpServer
      .fold(throw new RuntimeException("Could not create server")) {
        _.start(config.getString("guernica.api.host"))
      }

    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete {
        case Failure(exception) => exception.printStackTrace()
        case Success(_) => system.terminate()
      }
  }
}
