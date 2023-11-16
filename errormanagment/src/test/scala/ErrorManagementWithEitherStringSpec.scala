import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ErrorManagementWithEitherStringSpec extends AnyFlatSpec with Matchers {

  trait AccountStatus
  case object Open extends AccountStatus
  case object Closed extends AccountStatus

  case class BankAccount(availableMoney : Double, status : AccountStatus)
  def withDraw(amount: Double, bankAccount: BankAccount): Either[String, BankAccount] = {
    if (amount < 0) {
      Left("amount should be greater that 0 ")
    } else if (amount > bankAccount.availableMoney) {
      Left("amount should be lower that available money")
    } else if (bankAccount.status == Closed){
      Left("Account should be open")
    } else {
      Right(bankAccount.copy(availableMoney = bankAccount.availableMoney - amount))
    }
  }

  "Withdrawing more money than the bank account holds" should "return Left with error message " in {
    val emptyBankAccount = BankAccount(0, Open)
    withDraw(amount = 20_000, bankAccount = emptyBankAccount) shouldBe Left("amount should be lower that available money")
  }

  "Withdrawing negative money" should " return false" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    withDraw(-100, bankAccountWithMoney) shouldBe Left("amount should be greater that 0 ")
  }
  "Withdrawing on closed money" should "return false" in {
    val bankAccountWithMoney = BankAccount(10_000, Closed)
    withDraw(100, bankAccountWithMoney) shouldBe Left("Account should be open")
  }

  "withdrawing money from an adequately funded account" should "return true" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    withDraw(100, bankAccountWithMoney) shouldBe Right(BankAccount(9_900, Open))
  }

  "(using flatMap) multiple cash withdrawals  must work with an adequately funded account" should "return true" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)

    val bankAccountOneWithdrawOrError: Either[String, BankAccount] =  withDraw(100, bankAccountWithMoney)
    bankAccountOneWithdrawOrError shouldBe Right(BankAccount(9_900, Open))

    val bankAccountWithTwoWithdraw: Either[String, BankAccount] = bankAccountOneWithdrawOrError.flatMap(bankAccount => withDraw(100, bankAccount))
    bankAccountWithTwoWithdraw  shouldBe Right(BankAccount(9_800, Open))

    val bankAccountWithThreeWithdraw: Either[String, BankAccount] = bankAccountWithTwoWithdraw.flatMap(bankAccount => withDraw(100, bankAccount))
    bankAccountWithThreeWithdraw shouldBe Right(BankAccount(9_700, Open))
  }

  "(using for comprehension) multiple cash withdrawals must work with an adequately funded account" should "return true" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)

    val bankAccountOrError = for {
      bankAccountOneWithdraw <- withDraw(100, bankAccountWithMoney)
      bankAccountTwoWithdraw <- withDraw(100,bankAccountOneWithdraw)
      bankAccountThreeWithdraw <- withDraw(100,bankAccountTwoWithdraw)
    } yield bankAccountThreeWithdraw

    bankAccountOrError shouldBe Right(BankAccount(9_700, Open))
  }

}
