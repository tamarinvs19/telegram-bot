package org.bot.data.base
import org.bot.storage.classes.{Calendar, Event}
import org.bot.user.User


sealed trait Repository[T] {
  def add(element: T): Int = ???
  def delete(elementId: Int): Nothing = ???
  def has(elementId: Int): Boolean = ???
  def find(elementId: Int): T = ???
}

case object CalendarRepository extends Repository[Calendar] {
}

case object EventRepository extends Repository[Event] {
  def deleteOutdatedEvents(): Nothing = ???
}

case object UserRepository extends Repository[User] {
}
