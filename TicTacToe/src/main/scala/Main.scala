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
  var dateOfBirth: Instant = null
  var address: String = null
  override def toString : String = {
    s"$firstName $lastName $dateOfBirth"
  }

  override def equals(that: Any): Boolean = {
    that match {
      case that: Employee2 => this.firstName == that.firstName && this.lastName == that.lastName
      case _ => false
    }
  }

  override def hashCode(): Int = {
    firstName.hashCode() + lastName.hashCode()
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

object Main {
  def main(args: Array[String]): Unit = {}
}
