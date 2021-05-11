package org.bot.ConcurrentHashMap

import cats.effect.Sync
import org.bot.ID
import org.bot.traits.Repository

import java.util.concurrent.ConcurrentHashMap
import scala.jdk.CollectionConverters.CollectionHasAsScala

class ConcurrentHashMapRepository[F[_]: Sync, T: HavingId] extends Repository[F, ID, T]{
  val hashMap: ConcurrentHashMap[ID, T] = new ConcurrentHashMap[ID, T]()

  override def retrieve(id: ID): F[Either[String, T]] = Sync[F].delay(
    hashMap.get(id) match {
      case null  => Left("No objects with id = $id")
      case other => Right(other)
    }
  )

  override def getAll: F[List[(ID, T)]] = Sync[F].delay(
    hashMap.entrySet().asScala.toList.map(m => (m.getKey, m.getValue))
  )

  override def save(data: T): F[Unit] = Sync[F].delay(
    hashMap.put(implicitly[HavingId[T]].id(data), data)
  )

  override def exists(id: ID): F[Boolean] = Sync[F].delay(
    hashMap.containsKey(id)
  )

  override def remove(id: ID): F[Either[String, Unit]] = Sync[F].delay(
    hashMap.remove(id) match {
      case null => Left("No object with id = $id")
      case _    => Right()
    }
  )

  override def update(id: ID, data: T): F[Unit] = Sync[F].delay(
    hashMap.replace(id, data)
  )

  override def filter(f: T => Boolean): F[List[T]] = Sync[F].delay(
    hashMap.values().asScala.toList.filter(f)
  )
}
