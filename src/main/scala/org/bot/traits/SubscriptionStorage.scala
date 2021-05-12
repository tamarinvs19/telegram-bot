package org.bot.traits

import org.bot.{SourceType, User}

trait SubscriptionStorage[F[_]] {
  def subscribe(user: User, sourceType: SourceType): F[Unit]
  def unsubscribe(user: User, sourceType: SourceType): F[Unit]
  def notifySubscribers(sourceType: SourceType): F[List[User]]
}

