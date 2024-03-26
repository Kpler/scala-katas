import scala.annotation.tailrec
import scala.math.{ abs, max }

class Employee(val firstName: String, val lastName: String) {
  override def toString : String = {
   s"$firstName  $lastName"
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val employee = new Employee("Daniel", "Merkel")
    println(employee)
  }
}