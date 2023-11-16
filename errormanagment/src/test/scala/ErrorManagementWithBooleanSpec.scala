import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ErrorManagementWithBooleanSpec extends AnyFlatSpec with Matchers {

  trait AccountStatus
  case object Open extends AccountStatus
  case object Closed extends AccountStatus

  case class BankAccount(availableMoney : Double, status : AccountStatus)
  def withDraw(amount: Double, bankAccount: BankAccount): Boolean = {
    if (amount < 0) {
      false
    } else if (amount > bankAccount.availableMoney) {
      false
    }else if (bankAccount.status == Closed){
      false
    }  else {
      true
    }
  }

  "Withdrawing more money than the bank account holds" should " return false" in {
    val emptyBankAccount = BankAccount(0, Open)
    withDraw(amount = 20_000, bankAccount = emptyBankAccount) shouldBe false
  }

  "Withdrawing negative money" should " return false" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    withDraw(-100, bankAccountWithMoney) shouldBe false
  }
  "Withdrawing on closed money" should "return false" in {
    val bankAccountWithMoney = BankAccount(0, Closed)
    withDraw(100, bankAccountWithMoney) shouldBe false
  }

  "withdrawing money from an adequately funded account" should "return true" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    withDraw(100, bankAccountWithMoney) shouldBe true
  }

}
