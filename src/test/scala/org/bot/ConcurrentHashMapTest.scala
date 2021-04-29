package org.bot

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers
import org.bot.ConcurrentHashMap.ConcurrentHashMapRepository
import org.bot.ConcurrentHashMap.IdImplicits.userId

class ConcurrentHashMapTest extends AnyFunSuite with Matchers {
  test("testing initialisation") {
    val users = new ConcurrentHashMapRepository[User]
    assert(users.getAll.isEmpty)
    assert(users.retrieve(ID(1)).isLeft)
  }
}
