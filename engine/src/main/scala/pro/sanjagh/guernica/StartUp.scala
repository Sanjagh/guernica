package pro.sanjagh.guernica

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import pro.sanjagh.guernica.application.rest.{HttpServer, Routes}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import scala.util.{Failure, Success}

object StartUp extends App {
  private implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "guernica")
  private implicit val executionContext: ExecutionContextExecutor = system.executionContext

  private val httpServer: HttpServer = HttpServer(Routes())


  startServer()

  private def startServer(): Unit = {
    val bindingFuture = httpServer.start("localhost")

    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete {
        case Failure(exception) => exception.printStackTrace()
        case Success(_) => system.terminate()
      }
  }
}
