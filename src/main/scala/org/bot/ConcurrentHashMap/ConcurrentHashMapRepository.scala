package org.bot.ConcurrentHashMap

import cats.{Defer, Monad}
import org.bot.ID
import org.bot.traits.Repository

import java.util.concurrent.ConcurrentHashMap
import scala.jdk.CollectionConverters.CollectionHasAsScala

class ConcurrentHashMapRepository[F[_]: Defer: Monad, T: HavingId] extends Repository[F, ID, T]{
  val hashMap: ConcurrentHashMap[ID, T] = new ConcurrentHashMap[ID, T]()

  override def retrieve(id: ID): F[Either[String, T]] = Defer[F].defer(Monad[F].pure(
    hashMap.get(id) match {
      case null  => Left("No objects with id = $id")
      case other => Right(other)
    }
  ))

  override def getAll: F[List[(ID, T)]] = Defer[F].defer(Monad[F].pure(
    hashMap.entrySet().asScala.toList.map(m => (m.getKey, m.getValue))
  ))

  override def save(data: T): F[Unit] = Defer[F].defer(Monad[F].pure(
    hashMap.put(implicitly[HavingId[T]].id(data), data)
  ))

  override def exists(id: ID): F[Boolean] = Defer[F].defer(Monad[F].pure(
    hashMap.containsKey(id)
  ))

  override def remove(id: ID): F[Either[String, Unit]] = Defer[F].defer(Monad[F].pure(
    hashMap.remove(id) match {
      case null => Left("No object with id = $id")
      case _    => Right()
    }
  ))

  override def update(id: ID, data: T): F[Unit] = Defer[F].defer(Monad[F].pure(
    hashMap.replace(id, data)
  ))

  override def filter(f: T => Boolean): F[List[T]] = Defer[F].defer(Monad[F].pure(
    hashMap.values().asScala.toList.filter(f)
  ))
}
