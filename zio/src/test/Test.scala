import zio._
import zio.test._

object firstEffectTest extends ZIOSpecDefault {
  def spec = test("first effect test"){
    val person = ZIO.succeed("Test")
    assertZIO(person)(Assertion.equalTo("Test"))
  }
}


