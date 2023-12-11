import org.scalatest.flatspec._
import org.scalatest.matchers._

class PasswordValidationSpec extends AnyFlatSpec with should.Matchers {

  "Validator" should "refuse any password with less than 8 characters" in {
    // Given
    val validator = new PasswordValidator()

    // When
    val result = validator.validatePassword("Kpler_1")

    // Then
    result should be (false)
  }

  "Validator" should "refuse any password with exactly 8 characters" in {
    // Given
    val validator = new PasswordValidator()

    // When
    val result = validator.validatePassword("Kpler_12")

    // Then
    result should be(false)
  }

  "Validator" should "refuse any password without a capital letter" in {
    // Given
    val validator = new PasswordValidator()

    // When
    val result = validator.validatePassword("kpler_1234")

    // Then
    result should be(false)
  }

  "Validator" should "refuse any password without a lowercase letter" in {
    // Given
    val validator = new PasswordValidator()

    // When
    val result = validator.validatePassword("KPLER_1234")

    // Then
    result should be(false)
  }

  "Validator" should "accept a password respecting all rules" in {
    // Given
    val validator = new PasswordValidator()

    // When
    val result = validator.validatePassword("Kpler_1234")

    // Then
    result should be(true)
  }
}


