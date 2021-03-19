package eval

final case class EvaluationError(private val message: String = "",
                                 private val cause: Throwable = None.orNull)
  extends Exception(message, cause)

//class EvaluationError(msg: String) extends RuntimeException(msg)
