package pro.sanjagh.guernica.application.model

final case class Degree private(value: Double) extends AnyVal
final case class Rect private(width: Double, height: Double)

sealed trait Action
object Action {
  case object Optimize extends Action
  final case class Rotate(degree: Degree) extends Action
  final case class Crop(rect: Rect) extends Action
  final case class Resize(rect: Rect) extends Action
}

class NewJobRequest(actions: Seq[Action], inputBucket: String, outputBucket: String)
