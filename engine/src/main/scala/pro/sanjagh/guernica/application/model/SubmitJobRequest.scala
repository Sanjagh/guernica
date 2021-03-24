package pro.sanjagh.guernica.application.model

import pro.sanjagh.guernica.domain.Action
import spray.json.RootJsonFormat

private[application] final case class SubmitJobRequest(
    action: Action,
    inputBucket: String,
    outputBucket: String
)

private[application] object SubmitJobRequest extends JsonSupport {
  implicit val submitJobRequestFormat: RootJsonFormat[SubmitJobRequest] =
    jsonFormat3(SubmitJobRequest.apply)
}
