package org.bot.ConcurrentHashMap

import cats.Id
import org.bot.ID
import org.bot.storage.HavingId
import org.bot.traits.Repository

import java.util.concurrent.ConcurrentHashMap
import scala.jdk.CollectionConverters.{CollectionHasAsScala, SetHasAsScala}

class ConcurrentHashMapRepository[T: HavingId] extends Repository[Id, ID, T]{
  val hashMap: ConcurrentHashMap[ID, T] = new ConcurrentHashMap[ID, T]()

  override def retrieve(id: ID): Id[Either[String, T]] =
    hashMap.get(id) match {
      case null  => Left("No objects with id = $id")
      case other => Right(other)
    }

  override def getAll: Id[List[(ID, T)]] = hashMap.entrySet().asScala.toList.map(m => (m.getKey, m.getValue))

  override def save(data: T): Id[Unit] = hashMap.put(implicitly[HavingId[T]].id(data), data)

  override def exists(id: ID): Id[Boolean] = hashMap.containsKey(id)

  override def remove(id: ID): Id[Either[String, Unit]] =
    hashMap.remove(id) match {
      case null => Left("No object with id = $id")
      case _  => Right()
    }

  override def update(id: ID, data: T): Id[Unit] = hashMap.replace(id, data)

  override def filter(f: T => Boolean): Id[List[T]] = hashMap.values().asScala.toList.filter(f)
}
