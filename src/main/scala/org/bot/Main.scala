package org.bot

import org.bot.ConcurrentHashMap.ConcurrentHashMapRepository
import org.bot.Telegram.Telegram
import cats.effect.IO


object Main {
  val users = new ConcurrentHashMapRepository[IO, User]
  val calendars = new ConcurrentHashMapRepository[IO, Calendar]
  val events = new ConcurrentHashMapRepository[IO, Event]

  def main(args: Array[String]): Unit = {
    val tg = new Telegram
    tg.runBot()
  }
}
