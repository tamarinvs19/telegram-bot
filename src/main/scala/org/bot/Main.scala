package org.bot

import org.bot.ConcurrentHashMap.ConcurrentHashMapStorage
import org.bot.ConcurrentHashMap.IdImplicits.{calendarId, eventId, userId}

object Main {
  val users = new ConcurrentHashMapStorage[User]
  val calendars = new ConcurrentHashMapStorage[Calendar]
  val events = new ConcurrentHashMapStorage[Event]

  val

  def main(args: Array[String]): Unit = {
  }
}
