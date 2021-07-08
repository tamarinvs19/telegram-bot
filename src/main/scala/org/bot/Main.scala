package org.bot

import org.bot.ConcurrentHashMap.ConcurrentHashMapRepository
import org.bot.Telegram._
import cats.effect.{ExitCode, IO, IOApp}
import sttp.client3.asynchttpclient.cats.AsyncHttpClientCatsBackend


object Main extends IOApp {
  val users = new ConcurrentHashMapRepository[IO, User]
  val calendars = new ConcurrentHashMapRepository[IO, Calendar]
  val events = new ConcurrentHashMapRepository[IO, Event]

  val user = new User(ID(150), "150")
  users.save(user).void

  implicit val context: Context[IO] = Context(users, events, calendars)

  override def run(args: List[String]): IO[ExitCode] = {
    AsyncHttpClientCatsBackend[IO]().flatMap { implicit backend =>
      val bot = new Telegram
      bot.run()
    }
  }.as(ExitCode.Success)
}
