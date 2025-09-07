// Exceptions break referential transparency.
// An expression is referentially transparent if it always evaluates to the same result, given the same inputs, and has no side effects.
// Exceptions are not type safe.
// The functional solution of returning errors as values is safer and retains referential transparency

def mean(xs: Seq[Double]): Double=
  if (xs.isEmpty)
    throw new ArithmeticException("mean of empty list !")
  else xs.sum / xs.length

def meanWithoutException(xs: Seq[Double]): Option[Double]=
  if (xs.isEmpty)
    None
  else Some(xs.sum / xs.length)