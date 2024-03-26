import scala.annotation.tailrec
import scala.math.{ abs, max }
import java.time.Instant


class Employee(val firstName: String, val lastName: String) {
  var dateOfBirth: Instant = null
  var address: String = null
  override def toString : String = {
   s"$firstName $lastName $dateOfBirth"
  }

  def ageOfEmployee(): Long = {
    (Instant.now().getEpochSecond() - dateOfBirth.getEpochSecond()) / 60 / 60 / 24 / 365
  }

  def setAdress(newAddress: String) = {
      address = newAddress
  }

  def setAdress(newAdress: String, newDateOfBirth: Instant) = {
      address = newAdress
      dateOfBirth = newDateOfBirth
  }

}

case class Employee2(firstName: String, lastName: String) {

}

object Main {
  def main(args: Array[String]): Unit = {
    val employee = new Employee("Daniel", "Merkel")
    val employeeTwo = Employee2("Ben", "Janowski")
    println(employee)
    println(employee.dateOfBirth)
    employee.dateOfBirth = Instant.parse(
      "2001-01-01T00:00:00Z"
    )
    println(employee.ageOfEmployee())
    println(employeeTwo)
  }
}
