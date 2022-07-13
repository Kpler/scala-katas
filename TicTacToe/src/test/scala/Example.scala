import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "A List" should "drop the first values" in {
    val stack = List(3, 2)
    stack.drop(1) should be (List(2))
  }

  "A List length" should "be of size 9" in {
    val valid_matrix = List.fill(9)(False)
    valid_matrix.length() should be 9
  }


}
