package pro.sanjagh.guernica.application.rest

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, _}
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future

class Routes private(basePath: String)(implicit system: ActorSystem[Nothing]) extends (HttpRequest => Future[HttpResponse]) {

  private val routes = {
    pathPrefix(basePath) {
      path("job") {
        post {
          complete(HttpEntity(ContentTypes.`application/json`, "{\"value\": Hello}"))
        }
      }
      path("health")(get(complete("health, Not yet implemented!")))
      path("stats")(get(complete("stats, Not yet implemented!")))
    }
  }

  override def apply(req: HttpRequest): Future[HttpResponse] = routes(req)
}

object Routes {
  def apply(basePath: String)(implicit system: ActorSystem[Nothing]): Option[Routes] = basePath match {
    case null | "" => None
    case _ => Some(new Routes(basePath))
  }
}
