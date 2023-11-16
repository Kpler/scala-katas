import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ErrorManagementWithEitherTraitSpec extends AnyFlatSpec with Matchers {

  trait AccountStatus

  case object Open extends AccountStatus

  case object Closed extends AccountStatus

  case class BankAccount(availableMoney: Double, status: AccountStatus)
  trait OperationError 
  case object AmountShouldPositive extends OperationError
  case object AmountShouldLowerThanAvailableMoney extends OperationError
  case object AccountShouldBeOpen extends OperationError

  def withDraw(amount: Double, bankAccount: BankAccount): Either[OperationError, BankAccount] = {
    if (amount < 0) {
      Left(AmountShouldPositive)
    } else if (amount > bankAccount.availableMoney) {
      Left(AmountShouldLowerThanAvailableMoney)
    } else if (bankAccount.status == Closed) {
      Left(AccountShouldBeOpen)
    } else {
      Right(bankAccount.copy(availableMoney = bankAccount.availableMoney - amount))
    }
  }

  "Withdrawing more money than the bank account holds" should "return Left with error message " in {
    val emptyBankAccount = BankAccount(0, Open)
    withDraw(amount = 20_000, bankAccount = emptyBankAccount) shouldBe Left(AmountShouldLowerThanAvailableMoney)
  }

  "Withdrawing negative money" should " return false" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    withDraw(-100, bankAccountWithMoney) shouldBe Left(AmountShouldPositive)
  }
  "Withdrawing on closed money" should "return false" in {
    val bankAccountWithMoney = BankAccount(10_000, Closed)
    withDraw(100, bankAccountWithMoney) shouldBe Left(AccountShouldBeOpen)
  }

  "withdrawing money from an adequately funded account" should "return true" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)
    withDraw(100, bankAccountWithMoney) shouldBe Right(BankAccount(9_900, Open))
  }

  "(using flatMap) multiple cash withdrawals  must work with an adequately funded account" should "return true" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)

    val bankAccountOneWithdrawOrError= withDraw(100, bankAccountWithMoney)
    bankAccountOneWithdrawOrError shouldBe Right(BankAccount(9_900, Open))

    val bankAccountWithTwoWithdraw= bankAccountOneWithdrawOrError.flatMap(bankAccount => withDraw(100, bankAccount))
    bankAccountWithTwoWithdraw shouldBe Right(BankAccount(9_800, Open))

    val bankAccountWithThreeWithdraw= bankAccountWithTwoWithdraw.flatMap(bankAccount => withDraw(100, bankAccount))
    bankAccountWithThreeWithdraw shouldBe Right(BankAccount(9_700, Open))
  }

  "(using for comprehension) multiple cash withdrawals must work with an adequately funded account" should "return true" in {
    val bankAccountWithMoney = BankAccount(10_000, Open)

    val bankAccountOrError = for {
      bankAccountOneWithdraw <- withDraw(100, bankAccountWithMoney)
      bankAccountTwoWithdraw <- withDraw(100, bankAccountOneWithdraw)
      bankAccountThreeWithdraw <- withDraw(100, bankAccountTwoWithdraw)
    } yield bankAccountThreeWithdraw

    bankAccountOrError shouldBe Right(BankAccount(9_700, Open))
  }

}
