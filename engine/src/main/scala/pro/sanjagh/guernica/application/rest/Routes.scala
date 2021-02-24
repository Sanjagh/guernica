package pro.sanjagh.guernica.application.rest

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Directives.{path, _}
import pro.sanjagh.guernica.application.model.SubmitJobRequest

import scala.concurrent.Future

/**
 * Server routes.
 *
 * @param basePath base-path that append to start of all routes
 * @param system   actor system for running server on
 */
class Routes private(basePath: String)(implicit system: ActorSystem[Nothing]) extends (HttpRequest => Future[HttpResponse]) {

  private val routes = {
    pathPrefix(basePath) {
      concat(
        path("job") {
          post {
            entity(as[SubmitJobRequest]) { s =>
              complete(HttpEntity(ContentTypes.`application/json`, "{\"value\": Hello}"))
            }
          }
        },
        path("health")(get(complete("health, Not yet implemented!"))),
        path("stats")(get(complete("stats, Not yet implemented!")))
      )
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
