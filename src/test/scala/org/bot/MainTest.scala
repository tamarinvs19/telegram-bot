package org.bot

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class MainTest extends AnyFunSuite with Matchers {
  test("testing nothing") {
    assert(15 * 10 == 5 * 30)
  }
}
