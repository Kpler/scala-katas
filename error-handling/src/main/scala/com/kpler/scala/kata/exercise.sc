def failingFn(i: Int): Int= {
    try {
        val x = 42 + 5
        x / i
    }
    catch {
        case e: Exception => 43
    }
}

failingFn(12)
failingFn(0)