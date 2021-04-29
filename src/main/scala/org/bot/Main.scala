package org.bot

import org.bot.ConcurrentHashMap.ConcurrentHashMapRepository
import org.bot.ConcurrentHashMap.IdImplicits.{calendarId, eventId, userId}

object Main {
  val users = new ConcurrentHashMapRepository[User]
  val calendars = new ConcurrentHashMapRepository[Calendar]
  val events = new ConcurrentHashMapRepository[Event]

  def main(args: Array[String]): Unit = {
    val user = User(ID(1), "Student")
    users.save(user)
    println(users.getAll)
  }
}
