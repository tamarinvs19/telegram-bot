package org.bot.traits

import org.bot.{Event, User}

trait Notifier[F[_]] {
  def notifyUser(user: User, event: Event): F[Unit]
}
