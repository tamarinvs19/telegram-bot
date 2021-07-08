package org.bot.Telegram

import cats.effect.IO
import cats.implicits.catsSyntaxApplicativeId
import com.bot4s.telegram.api.declarative.Commands
import com.bot4s.telegram.cats.{Polling, TelegramBot}
import org.bot.{Context, ID, User}
import sttp.client3.{SttpBackend, UriContext, asFile, basicRequest}
import sttp.client3.asynchttpclient.cats.AsyncHttpClientCatsBackend

import java.io.File
import java.nio.file.{Files, Paths}

class Telegram(implicit val context: Context[IO]) {
  val token: String = sys.env.getOrElse("TG_TOKEN", Files.readString(Paths.get("TG_TOKEN")))

  class MyTGBot(implicit val backend: SttpBackend[IO, Any])
    extends TelegramBot[IO](token, backend)
      with Polling[IO]
      with Commands[IO] {

      onCommand("/ping") { implicit msg =>
        reply("pong").void
      }

      onCommand("/reg") { implicit msg =>
        withArgs { args =>
          val emknId = args.head.toInt
          val temFile = File.createTempFile("assignments", "ics")
          val url = uri"https://emkn.ru/users/$emknId/assignments.ics"
          val request = basicRequest.get(url).response(asFile(temFile))

          val responseMessage = AsyncHttpClientCatsBackend[IO]()
            .flatMap(request.send(_))
            .map(response => response.statusText)

          for {
            status <- responseMessage
            alreadyExists <- context.users.exists(ID(emknId))
            message = (status, alreadyExists) match {
              case ("OK", true) => "This user already exists"
              case ("OK", false) => {
                val newUser = new User(ID(emknId), "Random")
                println("-----------------------------------------------------------")
                context.users.save(newUser)
                "OK"
              }
              case (other, _) => other
            }
            _ <- reply(message)
          } yield {
            temFile.delete()
          }
        }
      }

      onCommand("/get") { implicit msg =>
        withArgs { calendarName =>
          reply("pong").void
        }
      }
    }

  def run(): IO[Unit] = {
    AsyncHttpClientCatsBackend[IO]().flatMap {implicit backend =>
      val bot = new MyTGBot()
      bot.run()
    }
  }

}
