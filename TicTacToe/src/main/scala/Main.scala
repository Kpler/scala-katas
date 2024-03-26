import scala.annotation.tailrec
import scala.math.{ abs, max }
import java.time.Instant


class Employee(val firstName: String, val lastName: String) {
  var dateOfBirth: Instant = null
  override def toString : String = {
   s"$firstName $lastName $dateOfBirth"
  }
}

case class Employee2(firstName: String, lastName: String) {

}

object Main {
  def main(args: Array[String]): Unit = {
    val employee = new Employee("Daniel", "Merkel")
    val employeeTwo = Employee2("Ben", "Janowski")
    println(employee)
    println(employeeTwo)
  }
}
