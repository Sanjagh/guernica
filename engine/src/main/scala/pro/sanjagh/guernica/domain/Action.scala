package pro.sanjagh.guernica.domain

/** Action is an operation should be executed on a image.
  * It has five different subtype. only one-action-at-a-time could executed on an image.
  * note, only [[Action.Rotate]] & [[Action.Resize]] and [[Action.Crop]] and [[Action.CropResize]] could be requested,
  * [[Action.Optimize]] will executed on every image automatically.
  */
sealed trait Action extends Product with Serializable
object Action extends ActionFactory {

  /** ValueClass for degree. like ration degree.
    */
  final case class Degree private (value: Double) extends AnyVal

  /** This is size. actually this is border rectangle that surrounded an image.
    */
  final case class Rect private (width: Double, height: Double)

  /** Optimizing image. means try to decrease image size without large change in quality.
    * this is a primary operation and should be executed on any image.
    */
  case object Optimize extends Action

  /** Rotating an image by given [[degree]].
    *
    * @param degree degree of rotation
    */
  final case class Rotate private (degree: Degree) extends Action

  /** Cropping an image to given [[rect]].
    *
    * @param rect new size of the image
    */
  final case class Crop private (rect: Rect) extends Action

  /** Stretching an image to fills out in given [[rect]].
    * this could breaks image ratio. because this operation did not check final ratio and current ratio of the image.
    * for ratio-aware type of this operation use [[CropResize]]
    *
    * @param rect final size of the image
    */
  final case class Resize private (rect: Rect) extends Action

  /** Stretching an image to fills out in given [[resizeRect]] in ratio-aware manner.
    * this tries keep image ratio, so first comparing first and final ratio of the image,
    * then crop image in a appropriate ratio then resize that.
    *
    * @param resizeRect final size of the image
    */
  final case class CropResize private (resizeRect: Rect) extends Action

  import GuernicaException._

  case object NegativeDegreeException
      extends InvalidArgumentException(
        title = "Degree could not be negative.",
        subtype = Some("NegativeDegreeException/Action")
      )
  case object NegativeWidthException
      extends InvalidArgumentException(
        title = "Width could not be negative.",
        subtype = Some("NegativeWidthException/Action")
      )
  case object NegativeHeightException
      extends InvalidArgumentException(
        title = "Height could not be negative.",
        subtype = Some("NegativeHeightException/Action")
      )
}
