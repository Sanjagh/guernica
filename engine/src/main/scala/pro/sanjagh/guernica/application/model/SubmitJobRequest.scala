package pro.sanjagh.guernica.application.model

import pro.sanjagh.guernica.domain.{Action, JsonSupport}
import spray.json.RootJsonFormat

case class SubmitJobRequest(action: Action, inputBucket: String, outputBucket: String)

object SubmitJobRequest extends JsonSupport {
  implicit val submitJobRequestFormat: RootJsonFormat[SubmitJobRequest] = jsonFormat3(SubmitJobRequest.apply)
}