package pro.sanjagh.guernica

import akka.actor.testkit.typed.scaladsl.ActorTestKit
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.{BeforeAndAfterAll, OptionValues}

abstract class UnitSpec extends AnyWordSpec with BeforeAndAfterAll with OptionValues with Matchers {
  val testKit: ActorTestKit = ActorTestKit()

  override protected def afterAll(): Unit = testKit.shutdownTestKit()
}