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

  test("testing save and retrieve") {
    val users = new ConcurrentHashMapRepository[User]
    val student = User(ID(1), "Student")
    users.save(student)
    assert(users.retrieve(ID(1)).isRight)
    assert(users.retrieve(ID(1)) == Right(student))
  }

  test("testing exists") {
    val users = new ConcurrentHashMapRepository[User]
    val student = User(ID(1), "Student")
    users.save(student)
    assert(users.exists(ID(1)) == true)
  }

  test("testing remove") {
    val users = new ConcurrentHashMapRepository[User]
    val student = User(ID(1), "Student")
    users.save(student)
    assert(users.remove(ID(1)).isRight)
    assert(users.exists(ID(1)) == false)
  }

  test("testing remove nonexistent") {
    val users = new ConcurrentHashMapRepository[User]
    val student = User(ID(1), "Student")
    users.save(student)
    assert(users.remove(ID(2)).isLeft)
    assert(users.exists(ID(1)) == true)
  }

  test("testing update") {
    val users = new ConcurrentHashMapRepository[User]
    val student = User(ID(1), "Student")
    users.save(student)
    users.update(ID(1), User(ID(1), "New name"))
    assert(users.retrieve(ID(1)) == Right(User(ID(1), "New name")))
  }

  test("testing filter") {
    val users = new ConcurrentHashMapRepository[User]
    val student = User(ID(1), "Student")
    val student2 = User(ID(2), "Student")
    users.save(student)
    users.save(student2)
    assert(users.filter(f => f.emknId == ID(1)).length == 1)
  }
}
