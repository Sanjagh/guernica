package pro.sanjagh.guernica.domain

/** Factory for creating [[Action]] instance.
  */
trait ActionFactory {
  import Action._

  // TODO : Can convert degrees greater than 360 to (degree - 360).
  /** Factory method for creating [[Degree]] instance.
    *
    * @param value value of the degree. could not be negative
    * @return one of [[NegativeDegreeException]] (if value be negative) or [[Degree]]
    */
  def degree(value: Double): Either[GuernicaException, Degree] =
    if (value < 0) Left(NegativeDegreeException) else Right(Degree(value))

  /** Factory method for creating [[Rect]] instance.
    *
    * @param width width of the rect. could be positive
    * @param height height of the rect. could be positive
    * @return one of [[NegativeWidthException]] or [[NegativeHeightException]]
    *         (if width or height be negative) or [[Rect]]
    */
  def rect(width: Double, height: Double): Either[GuernicaException, Rect] =
    if (width < 0) Left(NegativeWidthException)
    else if (height < 0) Left(NegativeHeightException)
    else Right(Rect(width, height))

  val optimize: Optimize.type = Optimize

  /** Creates new [[Rotate]] instance.
    *
    * @param degree of the rotation. zero value does not make any sense and will be ignored
    */
  def rotate(degree: Double): Either[GuernicaException, Rotate] = this.degree(degree).map(Rotate)

  /** Creates new [[Resize]] instance.
    *
    * @param width of the image. zero value does not make any sense and will be ignored
    * @param height of the image. zero value does not make any sense and will be ignored
    */
  def resize(width: Double, height: Double): Either[GuernicaException, Resize] =
    rect(width, height).map(Resize)

  /** Creates new [[Crop]] instance.
    */
  def crop(width: Double, height: Double): Either[GuernicaException, Crop] =
    rect(width, height).map(Crop)

  /** Creates new [[CropResize]] instance.
    */
  def cropResize(width: Double, height: Double): Either[GuernicaException, CropResize] =
    rect(width, height).map(CropResize)
}
