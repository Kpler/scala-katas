import org.scalatest._
import flatspec._
import matchers._
import Main._


class Example extends AnyFlatSpec with should.Matchers {

  "A List" should "drop the first values" in {
    val stack = List(3, 2)
    stack.drop(1) should be (List(2))
  }

  "A List length" should "be of size 9" in {
    val valid_matrix = List.fill(9)(0)
    valid_matrix.length == 9
  }

  "Player id" should "be 1 or 2" in {
    val valid_matrix = List.fill(9)(0)
    val matrix_after_move = playerMove(valid_matrix, 3, 3)
    matrix_after_move[3] == 1
  }

//  "Player id" should "be 1 or 2" in {
//    val valid_matrix = List.fill(9)(0)
//    val matrix_after_move = playerMove(valid_matrix, 3, 3)
//    matrix_after_move[3] == 1
//  }

}
