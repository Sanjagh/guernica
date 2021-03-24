package pro.sanjagh.guernica.domain

/** There is a 1-1 relationship between [[Action]] and [[Job]].
  */
final case class Job private (
    action: Action,
    input: InputBucket,
    output: OutputBucket
)

object Job {
  def apply(action: Action, input: InputBucket, output: OutputBucket): Job =
    new Job(action, input, output)
}
