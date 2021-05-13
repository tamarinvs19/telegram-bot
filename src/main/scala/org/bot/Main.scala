package org.bot

import org.bot.ConcurrentHashMap.ConcurrentHashMapRepository
import org.bot.Telegram._
import cats.effect.{ExitCode, IO, IOApp}


object Main extends IOApp {
  val users = new ConcurrentHashMapRepository[IO, User]
  val calendars = new ConcurrentHashMapRepository[IO, Calendar]
  val events = new ConcurrentHashMapRepository[IO, Event]

  override def run(args: List[String]): IO[ExitCode] = {
    val bot = new Telegram()
    bot.run()
  }
}
