import cats.data.Validated.Invalid
import cats.data.{Chain, ValidatedNec}
import cats.implicits._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
class ErrorManagementWithValidSpec extends AnyFlatSpec with Matchers {

  trait AccountStatus
  case object Open extends AccountStatus
  case object Closed extends AccountStatus
  case class BankAccount(availableMoney: Double, status: AccountStatus)
  trait OperationError
  case object AmountShouldPositive extends OperationError
  case object AmountShouldLowerThanAvailableMoney extends OperationError
  case object AccountShouldBeOpen extends OperationError

  def amountShouldBePositive(amount : Double): ValidatedNec[OperationError, Double] = {
    if (amount < 0) {
      AmountShouldPositive.invalidNec
    }else amount.validNec
  }

  def amountShouldLowerThanAvailableMoney(amount: Double, bankAccount: BankAccount): ValidatedNec[OperationError, Double] = {
    if (amount  > bankAccount.availableMoney) {
      AmountShouldLowerThanAvailableMoney.invalidNec
    } else amount.validNec
  }
  def accountShouldBeOpen(bankAccount: BankAccount): ValidatedNec[OperationError, BankAccount] = {
    if (bankAccount.status == Closed){
      AccountShouldBeOpen.invalidNec
    } else bankAccount.validNec
  }

  def withDraw(amount: Double, bankAccount: BankAccount): ValidatedNec[OperationError, BankAccount] = {
    val amountIsPositiveOrError: ValidatedNec[OperationError, Double] = amountShouldBePositive(amount)
    val amountIsLowerOrError: ValidatedNec[OperationError, Double] =  amountShouldLowerThanAvailableMoney(amount, bankAccount)
    val accountIsOpenOrError: ValidatedNec[OperationError, BankAccount] =  accountShouldBeOpen(bankAccount)
    ((amountIsPositiveOrError combine amountIsLowerOrError), accountIsOpenOrError).mapN(
      (validAmount, bankAccount) => bankAccount.copy(availableMoney = bankAccount.availableMoney- validAmount) )
  }

  "Withdrawing more money than the bank account holds on a close account" should "return all errors " in {
    val emptyAndClosedBankAccount = BankAccount(0, Closed)
    val bankAccountOrError = withDraw(amount = 20_000, bankAccount = emptyAndClosedBankAccount)

    bankAccountOrError shouldBe Invalid(Chain(AmountShouldLowerThanAvailableMoney, AccountShouldBeOpen))
  }

}
