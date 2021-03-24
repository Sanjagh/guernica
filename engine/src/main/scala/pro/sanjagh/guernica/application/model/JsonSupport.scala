package pro.sanjagh.guernica.application.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import pro.sanjagh.guernica.domain.Action._
import spray.json._

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  import pro.sanjagh.guernica.domain.Action

  implicit val degreeFormat: JsonFormat[Degree] = new JsonFormat[Degree] {
    override def write(obj: Degree): JsValue = JsNumber(obj.value)

    override def read(json: JsValue): Degree = json match {
      case JsNumber(value) => Degree(value.doubleValue)
      case _               => deserializationError("Double value needed.")
    }
  }
  implicit val rectFormat: RootJsonFormat[Rect] = jsonFormat2(Rect)

  implicit val rotateFormat: RootJsonFormat[Rotate] = jsonFormat1(Rotate)
  implicit val cropFormat: RootJsonFormat[Crop] = jsonFormat1(Crop)
  implicit val resizeFormat: RootJsonFormat[Resize] = jsonFormat1(Resize)
  implicit val cropAndResizeFormat: RootJsonFormat[CropAndResize] = jsonFormat2(
    CropAndResize
  )

  implicit val actionFormat: RootJsonFormat[Action] =
    new RootJsonFormat[Action] {
      override def write(obj: Action): JsValue =
        JsObject((obj match {
          case Optimize                     => JsObject()
          case rotate: Rotate               => rotate.toJson
          case crop: Crop                   => crop.toJson
          case resize: Resize               => resize.toJson
          case cropAndResize: CropAndResize => cropAndResize.toJson
        }).asJsObject.fields + ("type" -> JsString(obj.productPrefix)))

      override def read(json: JsValue): Action =
        json.asJsObject.getFields("type") match {
          case Seq(JsString("Rotate"))        => json.convertTo[Rotate]
          case Seq(JsString("Crop"))          => json.convertTo[Crop]
          case Seq(JsString("Resize"))        => json.convertTo[Resize]
          case Seq(JsString("CropAndResize")) => json.convertTo[CropAndResize]
        }
    }
}
