package pro.sanjagh.guernica.domain

/** Action is an operation should be executed on a image.
  * It has five different subtype. only one-action-at-a-time could executed on an image.
  * note, only [[Action.Rotate]] & [[Action.Resize]] and [[Action.Crop]] and [[Action.CropResize]] could be requested,
  * [[Action.Optimize]] will executed on every image automatically.
  */
sealed trait Action extends Product with Serializable
object Action {

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

  /** Factory method for creating [[Degree]] instance.
    *
    * @param value value of the degree. could not be negative
    * @return one of [[Exception.NegativeDegreeException]] (if value be negative) or [[Degree]]
    */
  def degree(value: Double): Either[Exception, Degree] =
    if (value < 0) Left(Exception.NegativeDegreeException) else Right(Degree(value))

  /** Factory method for creating [[Rect]] instance.
    *
    * @param width width of the rect. could be positive
    * @param height height of the rect. could be positive
    * @return one of [[Exception.NegativeWidthException]] or [[Exception.NegativeHeightException]]
    *         (if width or height be negative) or [[Rect]]
    */
  def rect(width: Double, height: Double): Either[Exception, Rect] =
    if (width < 0) Left(Exception.NegativeWidthException)
    else if (height < 0) Left(Exception.NegativeHeightException)
    else Right(Rect(width, height))

  val optimize: Optimize.type = Optimize

  /** Creates new [[Rotate]] instance.
    *
    * @param degree of the rotation. zero value does not make any sense and will be ignored
    */
  def rotate(degree: Double): Either[Exception, Rotate] = Action.degree(degree).map(Rotate)

  /** Creates new [[Resize]] instance.
    *
    * @param width of the image. zero value does not make any sense and will be ignored
    * @param height of the image. zero value does not make any sense and will be ignored
    */
  def resize(width: Double, height: Double): Either[Exception, Resize] =
    rect(width, height).map(Resize)

  /** Creates new [[Crop]] instance.
    */
  def crop(width: Double, height: Double): Either[Exception, Crop] = rect(width, height).map(Crop)

  /** Creates new [[CropResize]] instance.
    */
  def cropResize(width: Double, height: Double): Either[Exception, CropResize] =
    rect(width, height).map(CropResize)

  sealed trait Exception extends RuntimeException

  object Exception {
    case object NegativeDegreeException extends Exception

    case object NegativeWidthException extends Exception

    case object NegativeHeightException extends Exception
  }
}
