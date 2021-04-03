package pro.sanjagh.guernica.domain

import pro.sanjagh.guernica.domain.Bucket._

/** Job, is all operations that should be executed on the image in order.
  * first read image from [[input]], applying [[action]] on the image, then save result in the [[output]].
  *
  * @param action action that should be applied to image
  * @param input address of the source image which [[action]] should applied on it
  * @param output address of the result of the [[action]]
  */
final case class Job private (action: Action, input: Input, output: Output)

object Job {
  def apply(action: Action, input: Input, output: Output): Job =
    new Job(action, input, output)
}
