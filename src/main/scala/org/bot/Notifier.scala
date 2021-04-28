package org.bot

trait Notifier[F[_]] {
  def notifyUser(user: User, event: Event): F[Unit]
}
