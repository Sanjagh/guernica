package pro.sanjagh.guernica.domain

/** Base trait for all the exceptions in the guernica. see [[https://tools.ietf.org/html/rfc7807]] for more details
  */
trait GuernicaException extends RuntimeException {

  /** Typ of the exception.
    */
  val `type`: String

  /** Title of the exception. this could not be empty.
    */
  val title: String

  /** Optional description.
    */
  val description: Option[String]
}

object GuernicaException {

  /** Throws when a method is called with an invalid argument.
    */
  class InvalidArgumentException(
      val title: String,
      val subtype: Option[String] = None,
      val description: Option[String] = None
  ) extends GuernicaException {
    override val `type`: String = Some("sanjagh://guernica/InvalidArgument").flatMap { base =>
      subtype.map(s => s"$base/$s")
    }.get
  }
}
