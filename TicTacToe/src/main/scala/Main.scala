import scala.annotation.tailrec
import scala.math.{ abs, max }
import java.time.Instant
import java.time.temporal.Temporal


class Employee(val firstName: String, val lastName: String) {
  var dateOfBirth: Instant = null
  var address: String = null
  override def toString : String = {
   s"$firstName $lastName $dateOfBirth"
  }

  def ageOfEmployeeInSeconds(): Long = {
    val now = Instant.now()
    val age = now.getEpochSecond() - dateOfBirth.getEpochSecond()
    age
  }

  def getName(): String = {
    s"$firstName $lastName"
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
  def main(args: Array[String]): Unit = {}
}
