
//local reasoning = describe the entire of computation that would be perform
//referential transparency = replace a program with the value it evaluates too as many times as we like
// if replacing an expression with the value it evaluates change the behavior then it break referential transparency


//we would like to write pure program but at the same time side efect are inevitable

//we would like to have some data structure that bridge the concept of side effect with the concept of ref transparency and local reasoning
// Effect => it should describe the computation, the kind of value it produces
//        => we want to separate the construction from the execution, if side effect are require

// Is an Option an effect ?
// Example val anOption: Option[Int] = Option(10)
// Option is an effect (no side effect and validate the properties)

// Is Future an effect ?
// Does it describe computation  ?
// Yes there is a side effect bc we need to schedule the computation on a JVM thread
// Future is not an effect


object Effects extends App {

  // functional programming
  // EXPRESSIONS
  def combine(a: Int, b: Int): Int = a + b

  // local reasoning = type signature describes the kind of computation that will be performed
  // referential transparency = ability to replace an expression with the value that it evaluates to
  val five = combine(2, 3)
  val five_v2 = 2 + 3
  val five_v3 = 5

  println(five)
}