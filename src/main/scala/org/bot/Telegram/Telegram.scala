package org.bot.Telegram

import cats.effect.{ExitCode, IO}
import com.bot4s.telegram.api.declarative.Commands
import com.bot4s.telegram.cats.{Polling, TelegramBot}
import sttp.client3.SttpBackend
import sttp.client3.asynchttpclient.cats.AsyncHttpClientCatsBackend

import java.nio.file.{Files, Paths}

class Telegram{

  val token: String = sys.env.getOrElse("TG_TOKEN", Files.readString(Paths.get("TG_TOKEN")))

  class MyTGBot(implicit val backend: SttpBackend[IO, Any])
    extends TelegramBot[IO](token, backend)
      with Polling[IO]
      with Commands[IO] {

      onCommand("/ping") { implicit msg =>
        reply("pong").void
      }

      onCommand("/reg") { implicit msg =>
        reply("reg!!!").void
      }

      onCommand("/get") { implicit msg =>
        reply("get!!!").void
      }

//      onMessage { implicit msg =>
//        reply(msg.text.getOrElse("???")).void
//      }
    }

  def run(): IO[ExitCode] = {
    AsyncHttpClientCatsBackend[IO]().flatMap {implicit backend =>
      val bot = new MyTGBot()
      bot.run()
    }.as(ExitCode.Success)
  }

}
