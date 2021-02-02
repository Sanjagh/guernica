package pro.sanjagh.guernica.application.rest

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.adapter.ClassicActorSystemOps
import akka.http.scaladsl.testkit.ScalatestRouteTest
import pro.sanjagh.guernica.UnitSpec

class RoutesTest extends UnitSpec with ScalatestRouteTest {

  private implicit val typedSystem: ActorSystem[Nothing] = system.toTyped

  "Apply function" should {
    "return Some with valid string" in {
      val routes = Routes("basePath")

      routes shouldBe a[Some[_]]

      routes.value shouldNot be(null)
    }

    "return None with empty string" in {
      Routes("") should be(scala.None)
    }

    "return None with null string" in {
      Routes(null) should be(scala.None)
    }
  }
}
