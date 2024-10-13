import zio._


object ZIOEffects extends App{

  // success
  val meaningOfLife: ZIO[Any, Nothing, Int] = ZIO.succeed(42)
  // failure
  val aFailure: ZIO[Any, String, Nothing] = ZIO.fail("Something went wrong")
  // suspension/delay
  val aSuspendedZIO: ZIO[Any, Throwable, Int] = ZIO.suspend(meaningOfLife)


  case class MyIO[A](unsafeRun: () => A) {
    def map[B](f: A => B): MyIO[B] = ???

    def flatMap[B](f: A => MyIO[B]): MyIO[B] = ???
  }
  
  
}