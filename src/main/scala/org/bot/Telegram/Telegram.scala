package org.bot.Telegram

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.bot4s.telegram.api.declarative.Commands
import com.bot4s.telegram.cats.{Polling, TelegramBot}
import sttp.client3.SttpBackend
import sttp.client3.asynchttpclient.cats.AsyncHttpClientCatsBackend

import java.io.FileInputStream

class Telegram {

  val token: String = new String(new FileInputStream("TG_TOKEN").readAllBytes())

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

  def runBot() {
    AsyncHttpClientCatsBackend[IO]().flatMap {implicit backend =>
      val bot = new MyTGBot()
      bot.run()
    }.unsafeRunSync()
  }

}
