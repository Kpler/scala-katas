import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "A List" should "drop the first values" in {
    val stack = List(3, 2)
    stack.drop(1) should be (List(2))
  }
}
