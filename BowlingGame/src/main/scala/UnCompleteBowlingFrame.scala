trait UnCompleteBowlingFrame

case object EmptyFrame extends UnCompleteBowlingFrame

case class FirsRollFrame(score: Int) extends UnCompleteBowlingFrame

