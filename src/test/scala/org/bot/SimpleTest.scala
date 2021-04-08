package org.bot

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class SimpleTest extends AnyFunSuite with Matchers {
  import org.bot.SimpleCode.MY_CONST
  test("foo() multiples by MY_CONST") {
    assert(SimpleCode.foo(23) == 23 * MY_CONST)
  }
}
