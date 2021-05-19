package org.bot

import cats.{Defer, Monad}
import org.bot.traits.Repository

case class Context[F[_]: Defer: Monad](
                  users: Repository[F, ID, User],
                  events: Repository[F, ID, Event],
                  calendars: Repository[F, ID, Calendar],
                  )
