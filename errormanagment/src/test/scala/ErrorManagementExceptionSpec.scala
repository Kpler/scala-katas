import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * Referential transparency is a property of programming language expressions
 * which means that an expression can be replaced by
 * its value without changing the program's behavior.
 *
 * A pure function need Referential transparency
 */
class ErrorManagementExceptionSpec extends AnyFlatSpec with Matchers {

  trait AccountStatus
  case object Open extends AccountStatus
  case object Closed extends AccountStatus
  case class BankAccount(availableMoney : Double, status : AccountStatus)

  class NegativeAmountException extends RuntimeException
  class InsufficientFundsException extends RuntimeException
  class AccountClosedException extends RuntimeException

  def withDraw(amount: Double, bankAccount: BankAccount): BankAccount = {
    if (amount < 0) {
      throw new NegativeAmountException
    } else if (amount > bankAccount.availableMoney) {
      throw new InsufficientFundsException
    }else if (bankAccount.status == Closed){
      throw new AccountClosedException
    }
    bankAccount.copy(availableMoney = bankAccount.availableMoney-amount)
  }

  "Withdrawing more money than the bank account holds" should "throw a InsufficientFundsException exception" in {
    val emptyBankAccount = BankAccount(0, Open)
    assertThrows[InsufficientFundsException] {
      withDraw(amount = 20_000, bankAccount = emptyBankAccount)
    }
  }

  "Withdrawing negative money" should "throw a NegativeAmountException exception" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    assertThrows[NegativeAmountException] {
      withDraw(-100, bankAccountWithMoney)
    }
  }
  "Withdrawing on closed money" should "throw a AccountClosedException exception" in {
    val bankAccountWithMoney = BankAccount(200, Closed)
    assertThrows[AccountClosedException] {
      withDraw(100, bankAccountWithMoney)
    }
  }

  "withdrawing money from an adequately funded account" should "return unit" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    withDraw(100, bankAccountWithMoney) shouldBe (BankAccount(9_900, Open))
  }

}
