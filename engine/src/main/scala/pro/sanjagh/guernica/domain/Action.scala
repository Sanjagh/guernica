package pro.sanjagh.guernica.domain

sealed trait Action extends Product with Serializable
object Action {
  final case class Degree private (value: Double) extends AnyVal
  final case class Rect private (width: Double, height: Double)

  case object Optimize extends Action
  final case class Rotate(degree: Degree) extends Action
  final case class Crop(rect: Rect) extends Action
  final case class Resize(rect: Rect) extends Action
  final case class CropAndResize(cropRect: Rect, resizeRect: Rect) extends Action
}