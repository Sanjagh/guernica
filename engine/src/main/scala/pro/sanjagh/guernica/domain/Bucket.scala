package pro.sanjagh.guernica.domain

/** Amazon S3 bucket. input images could be on S3.
  * also, after [[Action]] applied on image, result will be written to S3.
  */
trait Bucket extends Any { def address: String }

object Bucket {

  /** ValueClass for input image address on S3.
    *
    * @param address address of the image on S3
    */
  final case class Input private (address: String) extends AnyVal with Bucket

  /** ValueClass for output image address on S3. result image will be written in this address.
    *
    * @param address address of the image on S3
    */
  final case class Output private (address: String) extends AnyVal with Bucket

  /** Factory method to create [[Input]] instance.
    *
    * @param address non-empty and nonNull string is S3 format
    */
  // TODO : Could I validate S3 address ? or I should trust on user ?
  def input(address: String): Input = Input(address)

  /** Factory method to create [[Output]] instance.
    *
    * @param address non-empty and nonNull string is S3 format
    */
  // TODO : Could I validate S3 address ?
  def output(address: String): Output = Output(address)
}
