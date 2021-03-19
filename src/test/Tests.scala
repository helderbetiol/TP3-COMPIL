package test

object InterpretTestGreen extends App with ITest with TestGreen {
  val verbose = true
  test()
}
object InterpretTestErrors extends App with ITest with TestErrors {
  val verbose = true
  test()
}
object InterpretTestBlue extends App with ITest with TestBlue {
  val verbose = true
  test()
}
object InterpretTestRed extends App with ITest with TestRed {
  val verbose = true
  test()
}

object CompileTestGreen extends App with CTest with TestGreen {
  val verbose = true
  test()
}
object CompileTestErrors extends App with CTest with TestErrors {
  val verbose = true
  test()
}
object CompileTestBlue extends App with CTest with TestBlue {
  val verbose = true
  test()
}
object CompileTestRed extends App with CTest with TestRed {
  val verbose = true
  test()
}

