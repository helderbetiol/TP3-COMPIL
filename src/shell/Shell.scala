package shell

object Shell extends App {
  val SHELL = "bash"

  test("echo $PATH > test/test.txt")
  test("wat2wasm test/test.wat -o test/test.wasm")

  def test(shellCmd: String): Unit = {
    val cmd = Array(SHELL, "-c", shellCmd)
    Runtime.getRuntime.exec(cmd).waitFor
  }
}