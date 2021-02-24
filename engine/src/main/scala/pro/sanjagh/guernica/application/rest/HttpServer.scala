package pro.sanjagh.guernica.application.rest

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding

import scala.concurrent.{ExecutionContextExecutor, Future}

class HttpServer private(routes: Routes)(
  implicit actorSystem: ActorSystem[Nothing],
  executionContext: ExecutionContextExecutor
) {

  /**
   * StartUp http server.
   *
   * @param host server host
   */
  def start(host: String): Future[ServerBinding] = Http().newServerAt(host, -1).bind(routes)
    .map { b =>
      println(s"Server online at ${b.localAddress}\nPress RETURN to stop...")
      b
    }
}

object HttpServer {
  def apply(routes: Routes)(
    implicit actorSystem: ActorSystem[Nothing],
    executionContext: ExecutionContextExecutor
  ): Option[HttpServer] = routes match {
    case null => None
    case _ => Some(new HttpServer(routes))
  }
}
