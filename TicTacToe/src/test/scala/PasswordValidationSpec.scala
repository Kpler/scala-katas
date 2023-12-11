import org.scalatest.flatspec._
import org.scalatest.matchers._

class PasswordValidationSpec extends AnyFlatSpec with should.Matchers {

  "Validator" should "refuse any password with less than 8 characters" in {
    val validator = new PasswordValidator()
    validator.validatePassword("Kpler_1") should be (false)
  }
}


