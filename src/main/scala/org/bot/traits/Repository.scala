package org.bot.traits

trait Repository[F[_], Id, Data] {
  def retrieve(id: Id): F[Either[String, Data]]
  def getAll: F[List[(Id, Data)]]
  def save(data: Data): F[Unit]
  def exists(id: Id): F[Boolean]
  def remove(id: Id): F[Either[String, Unit]]
  def update(id: Id, data: Data): F[Unit]
}
