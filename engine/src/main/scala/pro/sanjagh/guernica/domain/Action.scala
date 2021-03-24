package pro.sanjagh.guernica.domain

/** Action defined an operation should be executed on a file.
  */
sealed trait Action extends Product with Serializable
object Action {
  final case class Degree private (value: Double) extends AnyVal
  final case class Rect private (width: Double, height: Double)

  /** Optimization is a primary function and should be executed on any file.
    */
  case object Optimize extends Action
  final case class Rotate(degree: Degree) extends Action
  final case class Crop(rect: Rect) extends Action
  final case class Resize(rect: Rect) extends Action
  final case class CropAndResize(cropRect: Rect, resizeRect: Rect)
      extends Action
}
