package test

import calc.Calc

import java.io.{File, FileInputStream}
import scala.io.Source

/**
 * @author Jacques Noye
 * @version 1.0
 * @since 2021-03-17
 */
trait CTest {
  var count = 0
  var success = 0

  def report(): Unit = println(s"$success successful tests out of $count")

  // assumes the path environment variable has been updated so that bash (and wabt) is in the path
  val SHELL = "bash"
  val CC = "wat2wasm"
  val EXEC = "wasm-interp"
  val EXEC_SUFFIX = "--run-all-exports"

  /**
   * Executes a test.
   *
   * @param verbose     Verbose mode. Assumes compiler also has a verbose mode (option "-v" as
   *                    second argument).
   * @param fileName    Name of file to compile (relative to project root).
   * @param test        Description of the test.
   * @param expectation Expected result.
   */
  def test(verbose: Boolean, fileName: String, test: String, expectation: Option[Int]): Unit = {
    count += 1
    val args0 = if (verbose) new Array[String](2) else new Array[String](1)
    args0(0) = fileName
    if (verbose) args0(1) = "-v"
    println(s"==== $fileName : $test, attendu: $expectation")
    try {
      // TODO: use val
      var time0: Long = 0
      var time1: Long = 0
      val root = fileName.replaceFirst("\\.calc\\z", "")
      val cFileName = root + ".wat"
      val cFile = new File(cFileName)
      if (cFile.exists) time0 = cFile.lastModified
      try
        Calc.compile(new FileInputStream(fileName), Some(fileName))
      catch {
        case e: Exception => expectation match {
          case None =>
            println(e)
            println("SUCCESS on " + fileName)
            success += 1
          case _ =>
            println("FAILURE on " + fileName)
            println("==== Exception in compiler")
            e.printStackTrace()
        }
          return
      }
      if (cFile.exists) { // it should
        time1 = cFile.lastModified
      }
      if (time1 > time0) { // some code has been produced by the compiler
        // compile file
        val outFileName = compile(cFileName)
        val outFile = new File(outFileName)
        if (outFile.exists) {
          val time2 = outFile.lastModified
          if (time2 >= time1) { // a wasm file has been produced
            execute(outFileName)
            val result = display(root + ".txt")
            if (result == expectation) {
              println("SUCCESS on " + fileName)
              success += 1
            } else {
              println("FAILURE on " + fileName)
              println(fileName + " FAILURE with " + result)
            }
          } else {
            println("FAILURE on " + fileName)
            println("wat code does not compile")
          }
        } else {
          println("FAILURE on " + fileName)
          println("wat code does not compile")
        }
      }
      else { // no wat code produced
        expectation match {
          case None =>
            println("SUCCESS on " + fileName)
            success += 1
          case _ =>
            println("FAILURE on " + fileName)
            println("No wat code produced for " + fileName)
        }
      }
    } catch {
      case e: Exception =>
        System.err.println("==== Unexpected exception")
        e.printStackTrace()
    }
  }

  /**
   * Compiles input file.
   *
   * @param cFileName Name of input .wat file.
   * @return Name of output executable (.out) file.
   */
  private def compile(cFileName: String) = { //		String[] cmd = new String[3];
    //		cmd[0] = "/bin/sh";
    //		cmd[1] = "-c";
    //		cmd[2] = "/usr/bin/gcc " + CFilename;
    val outputFileName = cFileName.replaceFirst("\\.wat\\z", ".wasm")
    val cmd = Array(SHELL, "-c", s"$CC $cFileName -o $outputFileName")
    Runtime.getRuntime.exec(cmd).waitFor
    outputFileName
  }

  /**
   * Executes input file and logs result in .txt file.
   *
   * @param fileName Input executable (.out) file.
   */
  private def execute(fileName: String) = {
    val txtFileName = fileName.replaceFirst("\\.wasm\\z", ".txt")
    val cmd = Array(SHELL, "-c", EXEC + " " + fileName + " " + EXEC_SUFFIX + ">" + txtFileName)
    Runtime.getRuntime.exec(cmd).waitFor
  }

  /**
   * Reads result from .txt file.
   *
   * Assumes one-line result.
   *
   * @param txtFileName Input .txt file.
   * @return Contents of .txt as an Option[Int] (last line only, None otherwise).
   */
  private def display(txtFileName: String): Option[Int] = {
    val source = Source.fromFile(txtFileName)
    val contents = source.mkString
    source.close()
    // strip the result and interpret it as a signed integer
    decode(contents.stripPrefix("main() => i32:").stripLineEnd)
  }

  private def decode(b: String): Option[Int] = {
    val option = b.toLongOption
    option match {
      case None => None
      case Some(l) =>
        val option = l.toString.toIntOption
        option match {
          case Some(_) => option
          case None =>
            val minusOne:Long = "4294967295".toLong
            (minusOne - 1 - l).toString.toIntOption
      }
    }
  }

  /**
   * For testing purposes.
   *
   * @param shellCmd The shell command to be tested
   */
  def test(shellCmd: String) = {
    val cmd = Array(SHELL, "-c", shellCmd)
    Runtime.getRuntime.exec(cmd).waitFor
  }
}
