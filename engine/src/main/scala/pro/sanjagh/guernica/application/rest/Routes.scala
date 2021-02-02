package pro.sanjagh.guernica.application.rest

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, _}
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future

class Routes(basePath: String)(implicit system: ActorSystem[Nothing]) extends (HttpRequest => Future[HttpResponse]) {

  private val routes = {
    pathPrefix(basePath) {
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, "{\"value\": Hello}"))
        }
      }
    }
  }

  override def apply(req: HttpRequest): Future[HttpResponse] = routes(req)
}

object Routes {
  def apply(basePath: String)(implicit system: ActorSystem[Nothing]): Routes = new Routes(basePath)
}
