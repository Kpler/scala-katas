import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ErrorManagementExceptionSpec extends AnyFlatSpec with Matchers {

  trait AccountStatus
  case object Open extends AccountStatus
  case object Closed extends AccountStatus

  class NegativeAmountException extends RuntimeException
  class InsufficientFundsException extends RuntimeException
  class AccountClosedException extends RuntimeException

  case class BankAccount(availableMoney : Double, status : AccountStatus)
  def withDraw(amount: Double, bankAccount: BankAccount): Unit = {
    if (amount < 0) {
      throw new NegativeAmountException
    } else if (amount > bankAccount.availableMoney) {
      throw new InsufficientFundsException
    }else if (bankAccount.status == Closed){
      throw new AccountClosedException
    }
  }

  "Withdrawing more money than the bank account holds" should " return false" in {
    val emptyBankAccount = BankAccount(0, Open)
    assertThrows[InsufficientFundsException] {
      withDraw(amount = 20_000, bankAccount = emptyBankAccount)
    }
  }

  "Withdrawing negative money" should " return false" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    assertThrows[NegativeAmountException] {
      withDraw(-100, bankAccountWithMoney)
    }
  }
  "Withdrawing on closed money" should "return false" in {
    val bankAccountWithMoney = BankAccount(200, Closed)
    assertThrows[AccountClosedException] {
      withDraw(100, bankAccountWithMoney)
    }
  }

  "withdrawing money from an adequately funded account" should "return true" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    withDraw(100, bankAccountWithMoney) shouldBe ()
  }

}
