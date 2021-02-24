package pro.sanjagh.guernica.application.rest

import pro.sanjagh.guernica.UnitSpec

class HttpServerTest extends UnitSpec {

  "Apply function" should {
    import testKit.system
    import testKit.internalSystem.executionContext

    "return None with null Routes" in {
      HttpServer(null) should be (scala.None)
    }
  }
}
