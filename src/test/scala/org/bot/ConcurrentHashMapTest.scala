package org.bot

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class ConcurrentHashMapTest extends AnyFunSuite with Matchers {
  test("testing nothing") {
    assert(15 * 10 == 5 * 30)
  }
}
