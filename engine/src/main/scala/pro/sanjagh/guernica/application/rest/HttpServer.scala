package pro.sanjagh.guernica.application.rest

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

class HttpServer(routes: Routes)(implicit actorSystem: ActorSystem[Nothing], executionContext: ExecutionContextExecutor) {
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
  ): HttpServer = new HttpServer(routes)
}
