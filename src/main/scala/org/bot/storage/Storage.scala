package org.bot.storage

import org.bot.ID

trait Storage[T] {
  def dump(item: T): Unit
  def load(id: ID): Either[String, T]
}

trait Serializer[T] {
  def serialize(item: T): String
}

trait Deserializer[T] {
  def deserialize(data: String): Either[String, T]
}
