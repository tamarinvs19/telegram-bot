package org.bot.ConcurrentHashMap

import org.bot.ID
import org.bot.storage.{HavingId, Storage}

import java.util.concurrent.ConcurrentHashMap

class ConcurrentHashMapStorage[T: HavingId] extends Storage[T]{
  val data: ConcurrentHashMap[ID, T] = new ConcurrentHashMap[ID, T]()
  override def dump(item: T): Unit = {
    val keyId: ID = implicitly[HavingId[T]].id(item)
    data.put(keyId, item)
  }
  override def load(id: ID): Either[String, T] = {
    data.get(id) match {
      case null  => Left(s"No objects with id: $id")
      case other => Right(other)
    }
  }
}
