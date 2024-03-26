import org.scalatest._
import flatspec._
import matchers._

class Example extends AnyFlatSpec with should.Matchers {

  "two instances of employee" should "be have the same toString" in {
    val employee1 = new Employee("Daniel", "Merkel")
    val employee2 = new Employee("Daniel", "Merkel")
    employee1.getName().equals(employee2.getName()) shouldEqual true
    employee1.getName() == employee2.getName() shouldEqual true
  }

  "two instances of employee" should "be not the same instance" in {
    val employee1 = new Employee("Daniel", "Merkel")
    val employee2 = new Employee("Daniel", "Merkel")
    employee1.equals(employee2) shouldEqual false
    employee1 == employee2 shouldEqual false
  }
}
