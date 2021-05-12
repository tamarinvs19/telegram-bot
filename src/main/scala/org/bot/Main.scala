package org.bot

import cats.Eval
import org.bot.ConcurrentHashMap.ConcurrentHashMapRepository


object Main {
  val users = new ConcurrentHashMapRepository[Eval, User]
  val calendars = new ConcurrentHashMapRepository[Eval, Calendar]
  val events = new ConcurrentHashMapRepository[Eval, Event]

  def main(args: Array[String]): Unit = {
    val user = User(ID(1), "Student")
    users.save(user).value
    println(users.getAll.value)
  }
}
