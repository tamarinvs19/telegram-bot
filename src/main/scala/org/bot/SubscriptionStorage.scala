package org.bot

trait SubscriptionStorage[F[_]] {
  def subscribe(user: User, sourceType: SourceType): F[Unit]
  def unsubscribe(user: User, sourceType: SourceType): F[Unit]
  def notifySubscribers(sourceType: SourceType): F[List[User]]
}

