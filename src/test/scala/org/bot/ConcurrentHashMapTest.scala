package org.bot

import cats.Eval
import org.scalatest.funsuite.AnyFunSuite
import org.bot.ConcurrentHashMap.ConcurrentHashMapRepository
import org.scalatest.matchers.should.Matchers

class ConcurrentHashMapTest extends AnyFunSuite with Matchers {
  test("testing initialisation") {
    val users = new ConcurrentHashMapRepository[Eval, User]
    assert(users.getAll.value.isEmpty)
    assert(users.retrieve(ID(1)).value.isLeft)
  }

  test("testing save and retrieve") {
    val users = new ConcurrentHashMapRepository[Eval, User]
    val student = User(ID(1), "Student")
    users.save(student).value
    assert(users.retrieve(ID(1)).value.isRight)
    assert(users.retrieve(ID(1)).value == Right(student))
  }

  test("testing exists") {
    val users = new ConcurrentHashMapRepository[Eval, User]
    val student = User(ID(1), "Student")
    users.save(student).value
    assert(users.exists(ID(1)).value)
  }

  test("testing remove") {
    val users = new ConcurrentHashMapRepository[Eval, User]
    val student = User(ID(1), "Student")
    users.save(student).value
    assert(users.remove(ID(1)).value.isRight)
    assert(!users.exists(ID(1)).value)
  }

  test("testing remove nonexistent") {
    val users = new ConcurrentHashMapRepository[Eval, User]
    val student = User(ID(1), "Student")
    users.save(student).value
    assert(users.remove(ID(2)).value.isLeft)
    assert(users.exists(ID(1)).value)
  }

  test("testing update") {
    val users = new ConcurrentHashMapRepository[Eval, User]
    val student = User(ID(1), "Student")
    users.save(student).value
    users.update(ID(1), User(ID(1), "New name")).value
    assert(users.retrieve(ID(1)).value == Right(User(ID(1), "New name")))
  }

  test("testing filter") {
    val users = new ConcurrentHashMapRepository[Eval, User]
    val student = User(ID(1), "Student")
    val student2 = User(ID(2), "Student")
    users.save(student).value
    users.save(student2).value
    assert(users.filter(f => f.emknId == ID(1)).value.length == 1)
  }
}
